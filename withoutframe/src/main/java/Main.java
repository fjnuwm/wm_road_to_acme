public class Main {
  public static void main(String[] args) {
//    Add add = a -> {
//      a = a + 10;
//      System.out.println(a);
//    };
//    add.add(3);
//
//    Print print = () -> {
//      System.out.println(123);
//    };
//    print.print();

    doSomething(add1 -> {
      System.out.println(12345);
    });
  }

  public static void doSomething(AddAndPrint addAndPrint) {
    doSomething2(add -> {
      addAndPrint.addAndPrint(add);
      System.out.println(123456);
    });
  }

  public static void doSomething2(AddAndPrint addAndPrint) {
    addAndPrint.addAndPrint((a) -> {
    });
  }

  static interface Add {
    void add(int i);
  }

  static interface Print {
    void print();
  }

  static interface AddAndPrint {
    void addAndPrint(Add add);
    System.out.println(10 >> 3);
  }
}
