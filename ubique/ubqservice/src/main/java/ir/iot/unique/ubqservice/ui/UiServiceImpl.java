package ir.iot.unique.ubqservice.ui;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.device.enums.DeviceType;
import ir.iot.ubique.common.device.enums.SensorType;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.service.api.DBService;
import ir.iot.ubique.common.utility.DataBaseFactory;
import ir.iot.unique.ubqservice.api.UiService;

public class UiServiceImpl implements UiService {

  @Override
  public String addChannel(String name, String description, DeviceType type, SensorType sensor,
      String mac) throws DomainException {

    Device device = new Device(name, description, type, sensor, mac);

    DBService service = DataBaseFactory.getInstance().getDataAccessService();
    service.saveOrUpdateDevice(device);

    return device.getDeviceId();
  }

}
