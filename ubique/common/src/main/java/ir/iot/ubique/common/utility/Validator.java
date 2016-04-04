package ir.iot.ubique.common.utility;

public class Validator {

  public static boolean validateStringLength(String str, int maximumlen, int minimumlen) {

    if (str == null && minimumlen != 0)
      return false;
    if (str == null && minimumlen == 0)
      return true;
    return (str.length() >= minimumlen && str.length() <= maximumlen);
  }

  public static boolean validateDigitString(String str) {

    if (str == null)
      return true;
    return str.matches("[0-9]*");
  }

  public static boolean validateRequiredString(String str) {

    return (str != null && !str.isEmpty());
  }

  public static boolean validateUsername(String str) {

    if (str == null)
      return false;
    return str.matches("^.{5,}$");
  }

  public static boolean validatePassword(String str) {

    if (str == null)
      return false;
    return str.matches("^.{5,}$");
  }

  public static boolean validateConfirmPassword(String confrimPassword, String password) {

    if (confrimPassword == null)
      return false;
    if (confrimPassword.equals(password))
      return true;
    else
      return false;

  }

  public static boolean validateEmail(String str) {

    return isNull(str) || str.matches("^[A-Za-z0-9._%+-]+@(?:[A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
  }

  public static boolean validateNationalId(String str) {

    if ((str.length() != 10) || !validateDigitString(str))
      return false;
    return true;
  }

  public static boolean validateMonthString(String month) {

    if (month == null)
      return false;
    if (month.matches("[0-9]{1,2}")) {
      try {
        int monthInt = Integer.parseInt(month);
        if (monthInt > 12 || monthInt < 1)
          return false;
        return true;

      } catch (Exception e) {
        return false;
      }
    }
    return month.matches("[0-9]{1,2}");
  }

  public static boolean validateYearString(String year) {

    if (year == null)
      return false;
    return year.matches("[0-9]{1,2}");
  }

  public static boolean validateIP(String value) {

    return value == null || value.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}");
  }

  public static boolean validatePostalCode(String value) {

    return isNull(value) || (validateDigitString(value) && validateStringLength(value, 10, 10));
  }

  public static boolean validateDepositNumber(String value) {

    return isNull(value) || value.matches("[0-9]{1,4}\\.[0-9]{1,4}\\.[0-9]{1,8}\\.[0-9]{1,3}");
  }

  public static boolean validateCardNumber(String value) {

    return validateDigitString(value) && validateStringLength(value, 20, 13);
  }

  public static boolean validateCellPhoneSerialNumber(String value) {

    return isNull(value) || value.matches("[0-9]{15}");
  }

  public static boolean validatePhoneCodeNumber(String value) {

    return isNull(value) || value.matches("0[1-9][0-9]{1,4}");
  }

  public static boolean validatefaxCodeNumber(String value) {

    return isNull(value) || value.matches("0[1-9][0-9]{1,4}");
  }

  public static boolean validatePhoneNumber(String value) {

    return isNull(value) || (Validator.validateDigitString(value)
        && Validator.validateStringLength(value, 11, 10) && value.matches("0[1-9][0-9]{8,9}"));
  }

  public static boolean validateMACAddress(String value) {

    return isNull(value) || (Validator.validateStringLength(value, 17, 17)
        && value.matches("^([0-9a-zA-Z]{2}\\:){5}([0-9a-zA-Z]{2})$"));
  }

  public static boolean isNull(Object obj) {

    if (obj instanceof String) {
      String str = (String) obj;
      return str == null || str.equals("") || str.equalsIgnoreCase("null") || str.isEmpty();
    } else {
      return obj == null;
    }
  }

  public static boolean validateCompareValue(long value, long max, long min) {

    if (value >= min && value <= max) {
      return true;
    }
    return false;
  }

  public static boolean validateWebAddress(String str) {

    return isNull(str) || str.matches("(http://|https://)?([^.//]+.)+[^.]+");
  }

  public static boolean validateMobileNumberWithPrefix(String value, String prefix,
      int remainingDigitCount) {

    if (isNull(value))
      return false;
    return value.matches(prefix + "\\d{" + remainingDigitCount + "}");
  }

  public static boolean validateCardPIN2(String pin2) {
    return (pin2 != null) && pin2.matches("[0-9]{5,12}");
  }

}
