package ir.iot.ubique.common.utility;

import org.apache.log4j.Logger;

public class Generator {

  private static Logger logger = Logger.getLogger(Generator.class);

  public static Long currentId;

  public static String getRandomLowerCaseString(String type, int lenght) {

    String l = "abcdefghijklmnopqrstuvwxyz";

    String pool = "";
    pool += pool.concat(l);

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < lenght; i++) {
      builder.append(pool.charAt((int) (Math.random() * pool.length())));
    }

    return builder.toString();
  }

  public static String getRandomUpperCaseString(String type, int lenght) {

    String u = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    String pool = "";
    pool += pool.concat(u);

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < lenght; i++) {
      builder.append(pool.charAt((int) (Math.random() * pool.length())));
    }

    return builder.toString();
  }

  public static String getRandomNumber(int lenght) {

    String n = "123456789012345678901234567890";

    String pool = "";
    pool += pool.concat(n);

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < lenght; i++) {
      builder.append(pool.charAt((int) (Math.random() * pool.length())));
    }

    return builder.toString();
  }

  public static String generateId() {
    String result = "";
    long nextId = System.nanoTime();
    String randomString = Generator.getRandomNumber(6);
    result = nextId + randomString;
    return result;
  }

  public static String generateChId() {
    String result = "";
    long nextId = System.nanoTime();
    result = String.valueOf(nextId);
    return result;
  }

  public static String generateMessageId() {
    String result = "";
    long nextId = System.nanoTime();
    String randomString = Generator.getRandomNumber(5);
    result = nextId + randomString;
    return result;
  }

  public static String generateRequestId() {
    String result = "";
    long nextId = System.nanoTime();
    String randomString = Generator.getRandomNumber(5);
    result = nextId + randomString;
    return result;
  }

  public static void main(String[] args) {
    System.out.println(Generator.generateId());
    System.out.println(Generator.generateChId());
  }
}
