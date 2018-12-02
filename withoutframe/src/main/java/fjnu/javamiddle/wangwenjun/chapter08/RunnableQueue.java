package fjnu.javamiddle.wangwenjun.chapter08;

// 任务队列，用于存储提交到线程池中的任务
public interface RunnableQueue {
    // 新任务提交到任务队列中
    void offer(Runnable runnable);

    // 获取任务队列中的任务
    Runnable take();

    // 获取任务队列的数量
    int size();
}
