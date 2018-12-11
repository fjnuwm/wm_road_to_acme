package fjnu.javabase.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FutureDemo {
    public static void main(String[] args) throws Exception {
        Callable<Integer> callable = new Callable() {
            @Override
            public Object call() throws Exception {
                System.out.println("future task start");
                TimeUnit.SECONDS.sleep(10);
                return 10;
            }
        };

        FutureTask<Integer> futureTask = new FutureTask<>(callable);
        Thread thread = new Thread(futureTask);
        thread.start();
        int result = futureTask.get();
        System.out.println(result);
    }
}
