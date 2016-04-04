package ir.iot.ubique.common.exception.db;

public class DBSQLGrammerException extends DBException {

  private static final long serialVersionUID = -8303626083577867554L;

  public DBSQLGrammerException() {
    super();
  }

  public DBSQLGrammerException(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public DBSQLGrammerException(Throwable arg0) {
    super(arg0);
  }

  public DBSQLGrammerException(String msg) {
    super(msg);
  }
}
