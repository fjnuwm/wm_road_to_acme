package fjnu.javamiddle.wangwenjun.chapter07;

import java.util.concurrent.TimeUnit;

public class HookThread {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("hook1 is running");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {

            }
            System.out.println("hook1 will exit");
        }));

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("hook2 is running");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {

            }
            System.out.println("hook2 will exit");
        }));
        System.out.println("main 线程结束");
    }
}
