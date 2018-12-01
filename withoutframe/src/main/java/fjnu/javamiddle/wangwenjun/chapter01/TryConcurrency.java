package fjnu.javamiddle.wangwenjun.chapter01;

import java.util.concurrent.TimeUnit;

public class TryConcurrency {
  public static void main(String[] args) {
    new Thread(TryConcurrency::browseNews).start();
    listenMusic();
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
}
