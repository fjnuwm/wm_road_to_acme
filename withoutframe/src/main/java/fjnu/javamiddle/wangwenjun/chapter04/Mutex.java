package fjnu.javamiddle.wangwenjun.chapter04;

import java.util.concurrent.TimeUnit;

public class Mutex {
    private final Object mutex = new Object();

    public void accessResource() {
        synchronized (mutex) {
            try {
                TimeUnit.MINUTES.sleep(10);
            } catch (Exception e) {
            }
        }
    }

    public static void main(String[] args) {
        Mutex mutex = new Mutex();
        for (int i = 0; i < 5; i++) {
            new Thread(mutex::accessResource).start();
        }
    }
}
