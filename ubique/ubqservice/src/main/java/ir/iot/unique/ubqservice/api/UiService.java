package ir.iot.unique.ubqservice.api;

import ir.iot.ubique.common.device.enums.DeviceType;
import ir.iot.ubique.common.device.enums.SensorType;
import ir.iot.ubique.common.exception.DomainException;

public interface UiService {

  public String addChannel(String name, String description, DeviceType type, SensorType sensor,
      String mac) throws DomainException;
}
