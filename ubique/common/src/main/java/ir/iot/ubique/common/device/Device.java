package ir.iot.ubique.common.device;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import ir.iot.ubique.common.device.enums.DeviceType;
import ir.iot.ubique.common.device.enums.SensorType;
import ir.iot.ubique.common.utility.AbstractEntity;
import ir.iot.ubique.common.utility.Generator;

@Entity
@Table(name = "UBQ_DEVICE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DEVICE_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Device extends AbstractEntity {

  private static final long serialVersionUID = 6950126319797990819L;

  private String deviceId;
  private String name;
  private String description;
  private String url;
  private DeviceType type;
  private SensorType sensor;
  private String tags;
  private String macAddress;

  public Device() {
    this.deviceId = Generator.generateMessageId();
  }

  public Device(String name, String description, DeviceType type, SensorType sensor, String mac) {
    this.deviceId = Generator.generateMessageId();

    this.name = name;
    this.description = description;
    this.type = type;
    this.sensor = sensor;
    this.macAddress = mac;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getTags() {
    return tags;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public void getDeviceInfo() {

  }

  public DeviceType getType() {
    return type;
  }

  public void setType(DeviceType type) {
    this.type = type;
  }

  public SensorType getSensor() {
    return sensor;
  }

  public void setSensor(SensorType sensor) {
    this.sensor = sensor;
  }

  public String getMacAddress() {
    return macAddress;
  }

  public void setMacAddress(String macAddress) {
    this.macAddress = macAddress;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  @Override
  public String toString() {
    return "Device [name=" + name + ", description=" + description + ", url=" + url + ", tags="
        + tags + "]";
  }
}
