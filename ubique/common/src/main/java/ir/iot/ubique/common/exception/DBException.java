package ir.iot.ubique.common.exception;

public class DBException extends Exception {

  private static final long serialVersionUID = -3753385772172305871L;


  public DBException(String msg) {
    super(msg);
  }

  public DBException() {
    super();
  }


  public DBException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBException(Throwable arg0) {
    super(arg0);
  }

}
