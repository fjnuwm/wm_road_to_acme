package fjnu.javamiddle.wangwenjun.chapter02;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ThreadJoin {
    public static void main(String[] args) throws Exception {
        mainCallThreadsJoin();
    }

    public static Thread create(String name) {
        return new Thread(() -> {
           for (int i = 0; i < 10; i++) {
               System.out.println(Thread.currentThread().getName() + "#" + i);
           }
        }, name);
    }

    /**
     * join方法会使当前线程阻塞。例如以下方法，在main线程中调用线程的join方法，main方法会等到子线程的结束之后才能继续执行
     * @throws Exception
     */
    public static void mainCallThreadsJoin() throws Exception {
        List<Thread> threads = IntStream.range(1, 3).mapToObj(i -> {
            return create("wm" + i);
        }).collect(Collectors.toList());

        threads.forEach(Thread::start);
        for (Thread thread : threads) {
            thread.join();
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + "#" + i);
        }
    }
}
