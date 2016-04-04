package ir.iot.ubique.common.service;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import ir.iot.ubique.common.utility.ServiceKey;
import ir.iot.ubique.common.utility.Validator;


public class ServiceResult {

  private static final String SUCCESS = "success";
  private static final String MESSAGE = "message";
  private static final String DATA = "data";

  private boolean success = false;

  private String message;

  private Map<String, Object> data;

  public boolean getSuccess() {

    return success;
  }

  public void setSuccess(boolean success) {

    this.success = success;
  }

  public String getMessage() {

    return message;
  }

  public void setMessage(String message) {

    this.message = message;
  }

  public Map<String, Object> getData() {
    return data;
  }

  public void setData(Map<String, Object> data) {
    this.data = data;
  }

  public void addData(String key, Object value) {

    if (key == null)
      return;

    if (data == null)
      data = new HashMap<String, Object>();
    data.put((String) key, value);
  }

  public void addData(Map<String, Object> values) {

    if (values == null || values.isEmpty())
      return;

    if (data == null)
      data = new HashMap<String, Object>();
    data.putAll(values);
  }

  public String toString() {
    String result = "";
    result = toJSON().toJSONString();
    return result;
  }

  @SuppressWarnings("unchecked")
  public JSONObject toJSON() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put(SUCCESS, success);
    if (!Validator.isNull(dataToJSON()))
      jsonObject.put(DATA, dataToJSON());
    if (Validator.validateRequiredString(message))
      jsonObject.put(MESSAGE, message);

    return jsonObject;

  }

  @SuppressWarnings("unchecked")
  public JSONObject dataToJSON() {
    JSONObject jsonObject = null;
    if (!Validator.isNull(data)) {
      jsonObject = new JSONObject();
      jsonObject.putAll(data);
    }
    return jsonObject;
  }

  public Object getDataValue(String key) {

    Object result = "";
    if (data != null)
      result = data.get(key);
    return result;
  }


  public static void main(String[] args) {

    ServiceResult sr = new ServiceResult();
    // sr.addData(ServiceKey.SHARED_KEY, "123123123123");
    sr.addData(ServiceKey.AUTH, "00000000");
    sr.setSuccess(true);
    sr.setMessage("HASAN");
    System.out.println(sr.dataToJSON());
    System.out.println(sr.toJSON());
  }

}
