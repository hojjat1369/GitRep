package ir.iot.ubiqueui.rest.entity;

import java.io.Serializable;

public class JsonRequest implements Serializable {

  private static final long serialVersionUID = 7486037637399056602L;
  private String data;
  private String topic;

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

}
