package fjnu.javamiddle.wangwenjun.chapter05;

import java.util.LinkedList;

public class EventQueue {
    private final int MAX;

    static class Event {

    }

    private final LinkedList<Event> events = new LinkedList<>();

    private static final int DEFAULT_MAX = 10;

    public EventQueue() {
        this(DEFAULT_MAX);
    }

    public EventQueue(int max) {
        this.MAX = max;
    }

    public void offer(Event event) {
        synchronized (events) {
            if (events.size() > MAX) {
                try {
                    console(" the queue is full");
                    events.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            console("the new is submitted");
            events.addLast(event);
            events.notify();
        }
    }

    public Event take() {
        synchronized (events) {
            if (events.isEmpty()) {
                try {
                    console(" the queue is empty");
                    events.wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Event event = events.removeFirst();
            events.notify();
            console(" the event " + event + " is handled");
            return event;
        }
    }

    public void console(String message) {
        System.out.printf("%s:%s\n", Thread.currentThread().getName(), message);
    }
}
