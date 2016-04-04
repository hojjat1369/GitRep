package ir.iot.ubique.common.device.enums;

public enum DeviceType {

  Other(0),

  Camera(1),

  Computer(2),

  RaspberryPi(3),

  WeatherCenter(4),

  ;

  private int value;

  private DeviceType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
