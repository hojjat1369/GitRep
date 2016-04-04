package ir.iot.ubique.common.service;

import java.util.List;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.device.repository.DeviceRepository;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.exception.db.DBException;
import ir.iot.ubique.common.message.Message;
import ir.iot.ubique.common.message.repository.MessageRepository;
import ir.iot.ubique.common.service.api.DBService;

public class DBServiceImpl implements DBService {

  private DeviceRepository deviceRepository;
  private MessageRepository messageRepository;

  public DBServiceImpl() {
    deviceRepository = new DeviceRepository();
    messageRepository = new MessageRepository();
  }

  @Override
  public Long saveOrUpdateMessage(Message message) throws DomainException {
    Long id = null;
    try {
      id = messageRepository.saveOrUpdateMessage(message);
    } catch (DBException e) {
      e.printStackTrace();
      throw new DomainException(e.getMessage());
    }
    return id;
  }

  @Override
  public Long saveOrUpdateDevice(Device message) throws DomainException {

    Long id = null;
    try {
      id = deviceRepository.saveOrUpdateDevice(message);
    } catch (DBException e) {
      e.printStackTrace();
      throw new DomainException(e.getMessage());
    }
    return id;
  }

  @Override
  public List<Device> listDevice() throws DomainException {

    List<Device> devices = null;
    try {
      devices = deviceRepository.listDevice();
    } catch (DBException e) {
      e.printStackTrace();
      throw new DomainException(e.getMessage());
    }
    return devices;
  }

  @Override
  public List<Message> ListMessage(String topic) throws DomainException {
    List<Message> messages = null;
    try {
      messages = messageRepository.listMessage(topic);
    } catch (DBException e) {
      e.printStackTrace();
      throw new DomainException(e.getMessage());
    }
    return messages;
  }

}
