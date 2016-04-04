package ir.iot.ubique.common.exception.db;

public class DBNonUniqueResultException extends DBException {

  private static final long serialVersionUID = -3813059938745212753L;

  public DBNonUniqueResultException(String msg) {
    super(msg);
  }

  public DBNonUniqueResultException() {
    super();
  }

  public DBNonUniqueResultException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBNonUniqueResultException(Throwable arg0) {
    super(arg0);
  }
}
