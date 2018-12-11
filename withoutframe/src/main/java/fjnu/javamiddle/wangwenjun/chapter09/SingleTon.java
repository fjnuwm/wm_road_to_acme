package fjnu.javamiddle.wangwenjun.chapter09;

public class SingleTon {
    public static SingleTon singleTon = new SingleTon();
    public static int x = 0;
    public static int y;

    public SingleTon() {
        x++;
        y++;
    }

    public static void main(String[] args) {
        SingleTon singleTon = SingleTon.singleTon;
        System.out.println(singleTon.x);
        System.out.println(singleTon.y);
    }
}
