package ir.iot.ubique.common.device.enums;

public enum SensorType {

  Other(0),

  Accelerometer(1),

  AirFlow(2),

  Bend(3),

  Camera(4),

  GPS(5),

  ;

  private int value;

  private SensorType(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }
}
