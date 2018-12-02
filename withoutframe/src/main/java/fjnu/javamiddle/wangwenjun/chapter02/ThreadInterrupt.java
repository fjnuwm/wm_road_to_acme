package fjnu.javamiddle.wangwenjun.chapter02;

import java.util.concurrent.TimeUnit;

public class ThreadInterrupt {
    public static void main(String[] args) {
//        interruptBlock();
//        getThreadInterruptState();
        afterInterruptBlock();
    }

    private static void interruptBlock() {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.MINUTES.sleep(2);
            } catch (Exception e) {
                System.out.println("我被打断了");
            }
        });
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (Exception e) {

        }
        thread.interrupt();
    }

    private static void getThreadInterruptState() {
        try {
            Thread thread = new Thread(() -> {
                while (true){
                }
            });
            thread.start();
            TimeUnit.SECONDS.sleep(1);
            System.out.println("thread is interrupted?" + thread.isInterrupted());
            thread.interrupt();
            System.out.println("thread is interrupted?" + thread.isInterrupted());
        } catch (Exception e) {

        }
    }

    private static void afterInterruptBlock() {
        Thread thread = Thread.currentThread();
        System.out.println("current thread is interrupt?" + thread.isInterrupted());

        thread.interrupt();

        System.out.println("current thread is interrupt?" + thread.isInterrupted());

        try {
            TimeUnit.MINUTES.sleep(1);
        } catch (Exception e) {
            System.out.println(thread.getName() + " is interruptd");
        }
    }
}
