package ir.iot.ubique.common.exception.db;

public class DBHibernateException extends DBException {

  private static final long serialVersionUID = 6036333057780814014L;

  public DBHibernateException(String msg) {
    super(msg);
  }

  public DBHibernateException() {
    super();
  }

  public DBHibernateException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBHibernateException(Throwable arg0) {
    super(arg0);
  }


}
