package ir.iot.ubique.common.exception.db;

public class DBGenericJDBCException extends DBException {

  private static final long serialVersionUID = -4684114143791559807L;

  public DBGenericJDBCException(String msg) {
    super(msg);
  }

  public DBGenericJDBCException() {
    super();
  }

  public DBGenericJDBCException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBGenericJDBCException(Throwable arg0) {
    super(arg0);
  }
}
