package fjnu.javamiddle.wangwenjun.chapter05;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class BooleanLockTest {
    private Lock lock = new BooleanLock();

    private void syncMethod() {
        try {
            lock.lock();
            int randVal = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread().getName() + " get lock");
            TimeUnit.SECONDS.sleep(randVal);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    private void syncMethodWithMillis() {
        try {
            lock.lock(1000);
            int randVal = ThreadLocalRandom.current().nextInt(10);
            System.out.println(Thread.currentThread().getName() + " get lock");
            TimeUnit.SECONDS.sleep(randVal);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws Exception{
        BooleanLockTest booleanLockTest = new BooleanLockTest();
        // 模拟多个线程获取booleanLock锁
//        IntStream.range(0, 10).mapToObj(i -> new Thread(booleanLockTest::syncMethod)).forEach(Thread::start);

        // 模拟中断正在获取booleanLock的线程
//        Thread t1 = new Thread(booleanLockTest::syncMethod);
//        t1.start();
//        TimeUnit.MILLISECONDS.sleep(2);
//        Thread t2 = new Thread(booleanLockTest::syncMethod);
//        t2.start();
//        TimeUnit.MILLISECONDS.sleep(10);
//        t2.interrupt();

        // 模拟模拟获取booleanLock锁超时
        Thread t3 = new Thread(booleanLockTest::syncMethodWithMillis);
        t3.start();
        TimeUnit.MILLISECONDS.sleep(2);
        Thread t4 = new Thread(booleanLockTest::syncMethodWithMillis);
        t4.start();
    }
}
