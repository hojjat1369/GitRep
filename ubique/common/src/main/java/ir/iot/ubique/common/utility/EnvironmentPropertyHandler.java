package ir.iot.ubique.common.utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvironmentPropertyHandler {

  private static EnvironmentPropertyHandler propertyFileUtility;

  private static Properties properties;

  private EnvironmentPropertyHandler() {
    properties = new Properties();
    InputStream inputStream;
    try {
      ClassLoader classLoader = EnvironmentPropertyHandler.class.getClassLoader();
      // String fielePath = classLoader.getResourceAsStream("environment.test.properties");
      inputStream = classLoader.getResourceAsStream("environment.properties");
      // inputStream = new FileInputStream(fielePath);
      // inputStream = new FileInputStream("src/main/resources/environment.test.properties");
      properties.load(inputStream);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static EnvironmentPropertyHandler getInstance() {
    if (propertyFileUtility == null)
      propertyFileUtility = new EnvironmentPropertyHandler();
    return propertyFileUtility;
  }

  public String getProperty(String key) {
    String result = "";
    if (properties != null) {
      result = properties.getProperty(key);
    }
    return result;

  }

  public static void main(String[] args) {
    System.out
        .println(EnvironmentPropertyHandler.getInstance().getProperty(ServiceKey.DATA_ACCESS_MODE));
  }
}
