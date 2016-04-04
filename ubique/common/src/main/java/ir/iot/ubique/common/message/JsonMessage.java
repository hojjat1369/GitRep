package ir.iot.ubique.common.message;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "JSON")
public class JsonMessage extends Message {

  private static final long serialVersionUID = 117731163919432475L;

  private String topic;
  private String message;

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "JsonMessage [topic=" + topic + ", message=" + message + "]";
  }

}
