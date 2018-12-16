package fjnu.javabase.classloader;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
  public static void main(String[] args) throws Exception {
    InputStream in = Main.class.getResourceAsStream("123456wn");
    if (in == null) {
      System.out.println("null");
      return;
    }
    OutputStream out = new ByteArrayOutputStream();
    byte[] buff = new byte[1024];
    int n = 0;
    while ((n = in.read(buff)) != -1) {
      out.write(buff, 0, n);
    }
    System.out.println(((ByteArrayOutputStream) out).toString());

    System.out.println(Main.class.getResource("/"));
    System.out.println(Main.class.getResource(""));
  }
}
