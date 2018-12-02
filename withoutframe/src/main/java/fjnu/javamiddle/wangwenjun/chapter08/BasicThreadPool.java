package fjnu.javamiddle.wangwenjun.chapter08;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class BasicThreadPool extends Thread implements ThreadPool {
    // 初始化线程池大小
    private final int initSize;

    // 线程池最大线程数量
    private final int maxSzie;

    // 线程池核心数量
    private final int coreSize;

    // 当前线程活跃的线程数量
    private int activeCount;

    // 线程池是否被shutdown
    private volatile boolean isShutdown = false;

    // 创建线程的工厂
    private final ThreadFactory threadFactory;

    // 任务队列
    private final RunnableQueue runnableQueue;

    // 工作线程队列
    private final Queue<ThreadTask> threadQueue = new ArrayDeque<>();

    private static final DenyPolicy DEFAULT_DENY_POLICY = new DenyPolicy.DiscardDenyPolicy();

    private static final ThreadFactory DEFAULT_THREAD_FACTORY = new DefaultThreadFactory();

    private final long keepAliveTime;

    private final TimeUnit timeUnit;

    public BasicThreadPool(int initSize, int maxSzie, int coreSize, int queueSize) {
        this(initSize, maxSzie, coreSize, DEFAULT_THREAD_FACTORY, queueSize, DEFAULT_DENY_POLICY, 10, TimeUnit.SECONDS);
    }

    public BasicThreadPool(int initSize, int maxSzie, int coreSize,
                           ThreadFactory threadFactory, int queueSize,
                           DenyPolicy denyPolicy, long keepAliveTime,
                           TimeUnit timeUnit) {
        this.initSize = initSize;
        this.maxSzie = maxSzie;
        this.coreSize = coreSize;
        this.threadFactory = threadFactory;
        this.runnableQueue = new LinkedRunnableQueue(queueSize, denyPolicy, this);
        this.keepAliveTime = keepAliveTime;
        this.timeUnit = timeUnit;
        this.init();
    }

    private void init() {
        start();
        for (int i = 0; i < this.initSize; i++) {
            newThread();
        }
    }

    private void newThread() {
        InternalTask internalTask = new InternalTask(runnableQueue);
        Thread thread = this.threadFactory.createThread(internalTask);
        ThreadTask threadTask = new ThreadTask(thread, internalTask);
        threadQueue.offer(threadTask);
        this.activeCount++;
        thread.start();
    }

    private void removeThread() {
        ThreadTask threadTask = threadQueue.remove();
        threadTask.internalTask.stop();
        this.activeCount--;
    }

    @Override
    public void run() {
        while (!isShutdown && !isInterrupted()) {
            try {
                timeUnit.sleep(keepAliveTime);
            } catch (InterruptedException e) {
                isShutdown = true;
                break;
            }
            synchronized (this) {
                if (isShutdown) {
                    break;
                }

                // 当前任务队列中有未处理的任务且活动线程小于核心线程规定的数量
                if (runnableQueue.size() > 0 && activeCount < coreSize) {
                    for (int i = 0; i < coreSize - activeCount; i++) {
                        newThread();
                    }
                    continue;
                }

                // 当前任务队列中有未处理的任务且小于最大线程处理的数量
                if (runnableQueue.size() > 0 && activeCount < maxSzie) {
                    for (int i = 0; i < maxSzie - activeCount; i++) {
                        newThread();
                    }
                }

                // 当前任务队列中没有任务且活动的线程大于核心线程则回收线程
                if (runnableQueue.size() == 0 && activeCount > coreSize) {
                    for (int i = coreSize; i < activeCount; i++) {
                        removeThread();
                    }
                }
            }
        }
    }

    @Override
    public void excute(Runnable runnable) {
        if (isShutdown) {
            throw new IllegalThreadStateException("The thread pool is destroyed");
        }
        this.runnableQueue.offer(runnable);
    }

    @Override
    public void shutdown() {
        synchronized (this) {
            if (isShutdown) return;
            isShutdown = true;
            threadQueue.forEach(threadTask -> {
                threadTask.internalTask.stop();
                threadTask.thread.interrupt();
            });
            this.interrupt();
        }
    }

    @Override
    public int getInitSize() {
        if (isShutdown) {
            throw new IllegalThreadStateException("The thread pool is destroyed");
        }
        return this.initSize;
    }

    @Override
    public int getMaxSize() {
        if (isShutdown) {
            throw new IllegalThreadStateException("The thread pool is destroyed");
        }
        return this.maxSzie;
    }

    @Override
    public int getCoreSize() {
        if (isShutdown) {
            throw new IllegalThreadStateException("The thread pool is destroyed");
        }
        return this.coreSize;
    }

    @Override
    public int getQueueSize() {
        if (isShutdown) {
            throw new IllegalThreadStateException("The thread pool is destroyed");
        }
        return this.runnableQueue.size();
    }

    @Override
    public int getActiveCount() {
        synchronized (this) {
            return this.activeCount;
        }
    }

    @Override
    public boolean isShutdown() {
        return isShutdown;
    }

    private static class ThreadTask {
        Thread thread;
        InternalTask internalTask;

        public ThreadTask(Thread thread, InternalTask internalTask) {
            this.thread = thread;
            this.internalTask = internalTask;
        }
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger GROUP_COUNTER = new AtomicInteger(1);

        private static final ThreadGroup group = new ThreadGroup("MyThreadGroup-" + GROUP_COUNTER.getAndIncrement());

        private static final AtomicInteger COUNTER = new AtomicInteger(0);

        @Override
        public Thread createThread(Runnable runnable) {
            return new Thread(group, runnable, "thread-pool-" + COUNTER.getAndIncrement());
        }
    }
}
