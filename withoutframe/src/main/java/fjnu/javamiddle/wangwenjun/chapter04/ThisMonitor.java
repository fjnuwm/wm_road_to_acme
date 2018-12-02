package fjnu.javamiddle.wangwenjun.chapter04;

import java.util.concurrent.TimeUnit;

public class ThisMonitor {
    public synchronized void method1() {
        System.out.println(Thread.currentThread().getName() + " enter method1");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {

        }
    }

    public synchronized void method2() {
        System.out.println(Thread.currentThread().getName() + " enter method2");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
//        ThisMonitor thisMonitor = new ThisMonitor();
//        new Thread(thisMonitor::method1).start();
//        new Thread(thisMonitor::method2).start();
        new Thread(ThisMonitor::method3).start();
        new Thread(ThisMonitor::method4).start();
    }

    public static synchronized void method3() {
        System.out.println(Thread.currentThread().getName() + " enter method3");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {

        }
    }

    public static synchronized void method4() {
        System.out.println(Thread.currentThread().getName() + " enter method4");
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (Exception e) {

        }
    }
}
