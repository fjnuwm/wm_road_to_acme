package fjnu.javamiddle.wangwenjun.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
  public static void main(String[] args) {
    // 启动线程同时执行browseNew和listenMusic
//    new Thread(TryConcurrency::browseNews).start();
//    listenMusic();
    startSameThreadTwice();
    startCompletedThread();
  }

  private static void browseNews() {
    for (;;) {
      System.out.println("看新闻");
      sleep(1);
    }
  }

  private static void listenMusic() {
    for (;;) {
      System.out.println("听音乐");
      sleep(1);
    }
  }

  private static void sleep(int seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (Exception e) {

    }
  }

  private static void startSameThreadTwice() {
    // 同一线程启动两次会抛出异常
    Thread thread1 = new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(10);
      } catch (Exception e) {
        System.out.println(e);
      }
    });
    thread1.start();
    thread1.start();
  }

  private static void startCompletedThread() {
    // 线程执行完毕再次启动也会抛出异常
    Thread thread2 = new Thread(() -> {
      try {
        TimeUnit.SECONDS.sleep(1);
      } catch (Exception e) {
        System.out.println(e);
      }
    });
    thread2.start();
    try {
      TimeUnit.SECONDS.sleep(2);
    } catch (Exception e) {
      System.out.println(e);
    }
    thread2.start();
  }
}
