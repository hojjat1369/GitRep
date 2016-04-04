package ir.iot.ubiqueui.service.api;

import java.util.List;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.message.Message;

public interface UiService {

  public void addDevice(String name, String url, String topic) throws DomainException;

  public List<Device> getDevices() throws DomainException;

  public List<Message> getMessages(String topic) throws DomainException;

}
