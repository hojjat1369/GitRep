package ir.iot.ubique.common.device;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MQTT")
public class MqttDevice extends Device {

  private static final long serialVersionUID = 6950126319797990819L;

  private String topic;

  public String getTopic() {
    return topic;
  }

  public void setTopic(String topic) {
    this.topic = topic;
  }

  @Override
  public void getDeviceInfo() {

  }


}
