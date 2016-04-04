package ir.iot.ubique.common.utility;

import ir.iot.ubique.common.service.DBMockServiceImpl;
import ir.iot.ubique.common.service.DBServiceImpl;
import ir.iot.ubique.common.service.api.DBService;

public class DataBaseFactory {

  private static DataBaseFactory dataAccessFactory;
  DBService dataAccessService;

  private DataBaseFactory() {
    String runningMode =
        EnvironmentPropertyHandler.getInstance().getProperty(ServiceKey.DATA_ACCESS_MODE);

    dataAccessService = new DBMockServiceImpl();
    if (Validator.validateRequiredString(runningMode) && runningMode.equals(ServiceKey.DB)) {
      dataAccessService = new DBServiceImpl();
    }
  }

  public static DataBaseFactory getInstance() {
    if (dataAccessFactory == null)
      dataAccessFactory = new DataBaseFactory();
    return dataAccessFactory;
  }

  public DBService getDataAccessService() {
    return dataAccessService;
  }
}
