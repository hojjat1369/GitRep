package ir.iot.ubique.common.service;

import java.util.ArrayList;
import java.util.List;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.message.Message;
import ir.iot.ubique.common.service.api.DBService;

public class DBMockServiceImpl implements DBService {

  List<Device> devices;
  List<Message> messages;

  public DBMockServiceImpl() {
    devices = new ArrayList<>();
    messages = new ArrayList<>();
  }

  @Override
  public Long saveOrUpdateMessage(Message message) throws DomainException {
    if (message == null)
      throw new DomainException("Invalid message");
    Long id = System.nanoTime();
    message.setId(id);
    messages.add(message);
    return id;
  }

  @Override
  public Long saveOrUpdateDevice(Device device) throws DomainException {
    if (device == null)
      throw new DomainException("Invalid message");
    Long id = System.nanoTime();
    device.setId(id);
    devices.add(device);
    return id;
  }

  @Override
  public List<Device> listDevice() throws DomainException {
    return devices;
  }

  @Override
  public List<Message> ListMessage(String topic) throws DomainException {
    return messages;
  }

}
