package ir.iot.ubique.common.exception;

public class DomainException extends Exception {

  private static final long serialVersionUID = -3753385772172305871L;


  public DomainException(String msg) {
    super(msg);
  }

  public DomainException() {
    super();
  }


  public DomainException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DomainException(Throwable arg0) {
    super(arg0);
  }

}
