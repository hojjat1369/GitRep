package ir.iot.ubique.common.service.api;

import java.util.List;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.message.Message;

public interface DBService {

  public Long saveOrUpdateDevice(Device message) throws DomainException;

  public Long saveOrUpdateMessage(Message message) throws DomainException;

  public List<Device> listDevice() throws DomainException;
  
  public List<Message> ListMessage(String topic) throws DomainException;
}
