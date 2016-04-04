package ir.iot.ubique.common.exception.db;

public class DBNonUniqueException extends DBException {

  private static final long serialVersionUID = -4509031288792112249L;

  public DBNonUniqueException(String msg) {
    super(msg);
  }

  public DBNonUniqueException() {
    super();
  }

  public DBNonUniqueException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBNonUniqueException(Throwable arg0) {
    super(arg0);
  }

}
