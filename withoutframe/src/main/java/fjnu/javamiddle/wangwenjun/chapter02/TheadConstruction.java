package fjnu.javamiddle.wangwenjun.chapter02;

public class TheadConstruction {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        ThreadGroup threadGroup = new ThreadGroup("TestGroup");
        Thread t2 = new Thread(threadGroup, "t2");
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        System.out.println("Main belong the group:" + mainThreadGroup.getName());
        System.out.println("t1 group == main group:" + (t1.getThreadGroup() == mainThreadGroup));
        System.out.println("t2 group == main group:" + (t2.getThreadGroup() == mainThreadGroup));
        System.out.println("t2 group == testgroup:" + (t2.getThreadGroup() == threadGroup));
    }
}
