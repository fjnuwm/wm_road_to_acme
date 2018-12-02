package fjnu.javamiddle.wangwenjun.chapter08;

public interface ThreadPool {
    // 提交任务到线程池
    void excute(Runnable runnable);

    // 关闭线程池
    void shutdown();

    // 获取线程池的初始化大小
    int getInitSize();

    // 获取线程池的最大线程数
    int getMaxSize();

    // 获取线程池的核心数量大小
    int getCoreSize();

    // 获取线程池中用于缓存队列的大小
    int getQueueSize();

    // 获取线程池中活跃的线程数量
    int getActiveCount();

    // 线程池是否已被shutdown
    boolean isShutdown();
}
