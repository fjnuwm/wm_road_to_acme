package fjnu.javamiddle.wangwenjun.chapter05;

import java.util.concurrent.TimeUnit;

public class EventClient {
    public static void main(String[] args) {
        final EventQueue eventQueue = new EventQueue();
        new Thread(() -> {
            while (true) {
                eventQueue.offer(new EventQueue.Event());
            }
        }, "Producer").start();

        new Thread(() -> {
            while (true) {
                eventQueue.take();
                try {
                    TimeUnit.MILLISECONDS.sleep(30);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer").start();
    }
}

