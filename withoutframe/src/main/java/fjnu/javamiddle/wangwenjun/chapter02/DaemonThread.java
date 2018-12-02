package fjnu.javamiddle.wangwenjun.chapter02;

/**
 * 守护线程在jvm线程生命周期结束后会自动结束生命
 */
public class DaemonThread {
    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(() -> {
           while (true) {
               try {
                   Thread.sleep(1000);
               } catch (Exception e) {

               }
           }
        });
//        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000);
        System.out.println("main thread finish lifycycle");
    }
}
