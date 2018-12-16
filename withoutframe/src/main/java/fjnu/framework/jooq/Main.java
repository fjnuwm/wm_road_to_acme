package fjnu.framework.jooq;

import static fjnu.framework.jooq.generated.tables.Tables.*;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
  static final String USERNAME = "root";
  static final String PASSWORD = "";
  static final String DB_URL = "jdbc:mysql://localhost:3306/library";

  public static Connection getConn() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    } catch (Exception e) {

    }
    return conn;
  }

  public static DSLContext getDSL() {
    return DSL.using(getConn(), SQLDialect.MYSQL);
  }

  public static void main(String[] args) {
      DSLContext dslContext = getDSL();
      Result<Record> result = dslContext.select().from(AUTHOR).fetch();
      for (Record r : result) {
        System.out.println("id:" + r.getValue(AUTHOR.ID)
            + " firstname:" + r.getValue(AUTHOR.FIRST_NAME)
            + " lastname:" + r.getValue(AUTHOR.LAST_NAME));
      }
  }
}
