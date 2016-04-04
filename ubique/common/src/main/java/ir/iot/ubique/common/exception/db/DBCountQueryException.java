package ir.iot.ubique.common.exception.db;

public class DBCountQueryException extends DBException {

  private static final long serialVersionUID = -5018856335181362371L;

  public DBCountQueryException() {
    super();
  }

  public DBCountQueryException(String message, Throwable cause) {
    super(message, cause);
  }

  public DBCountQueryException(String message) {
    super(message);
  }

  public DBCountQueryException(Throwable cause) {
    super(cause);
  }

}
