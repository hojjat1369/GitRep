package ir.iot.ubique.common.exception.db;

public class DBConstraintViolationException extends DBException {

  private static final long serialVersionUID = -7582265216109785404L;

  public DBConstraintViolationException(String msg) {
    super(msg);
  }

  public DBConstraintViolationException() {
    super();
  }

  public DBConstraintViolationException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBConstraintViolationException(Throwable arg0) {
    super(arg0);
  }

}
