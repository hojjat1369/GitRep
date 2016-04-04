package ir.iot.ubique.common.exception.db;

public class DBQueryException extends DBException {

  private static final long serialVersionUID = 2810813621524807145L;

  public DBQueryException(String msg) {
    super(msg);
  }

  public DBQueryException() {
    super();
  }

  public DBQueryException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBQueryException(Throwable arg0) {
    super(arg0);
  }

}
