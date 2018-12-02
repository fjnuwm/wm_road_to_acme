package fjnu.javamiddle.wangwenjun.chapter05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class BooleanLock implements Lock {
    private Thread currentThread;

    private boolean locked = false;

    private List<Thread> blockThread = new ArrayList<>();
    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                Thread tempThread = Thread.currentThread();
                try {
                    if (!blockThread.contains(tempThread)) {
                        blockThread.add(tempThread);
                        this.wait();
                    }
                } catch (Exception e) {
                    blockThread.remove(tempThread);
                    System.out.println("移除被中断的线程:" + tempThread.getName());
                }
            }
            blockThread.remove(Thread.currentThread());
            locked = true;
            this.currentThread = Thread.currentThread();
        }
    }

    @Override
    public void lock(long millis) throws TimeoutException, InterruptedException {
        synchronized (this) {
            if (millis <= 0) {
                this.lock();
            } else {
                long remainingMillis = millis;
                long endMillis = System.currentTimeMillis() + millis;
                while (locked) {
                    if (remainingMillis < 0) {
                        throw new TimeoutException("can not get lock during " + millis);
                    }
                    if (!blockThread.contains(Thread.currentThread())) {
                        blockThread.add(Thread.currentThread());
                        this.wait(remainingMillis);
                        remainingMillis = endMillis - System.currentTimeMillis();
                    }
                }
                blockThread.remove(Thread.currentThread());
                locked = true;
                this.currentThread = Thread.currentThread();
            }
        }
    }

    @Override
    public void unlock() {
        synchronized (this) {
            if (Thread.currentThread() == currentThread) {
                this.locked = false;
                System.out.println(currentThread.getName() + " release lock");
                this.notifyAll();
            }
        }
    }

    @Override
    public List<Thread> getBlockedThreads() {
        return Collections.unmodifiableList(this.blockThread);
    }
}
