package ir.iot.ubique.common.message;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MQTT")
public class MqttMessage extends Message {

  private static final long serialVersionUID = 117731163919432475L;

  private String topic;
  private String message;
  private Integer qos;

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

  public Integer getQos() {
    return qos;
  }

  public void setQos(Integer qos) {
    this.qos = qos;
  }

  @Override
  public String toString() {
    return "MqttMessage [topic=" + topic + ", message=" + message + ", qos=" + qos + "]";
  }

}
