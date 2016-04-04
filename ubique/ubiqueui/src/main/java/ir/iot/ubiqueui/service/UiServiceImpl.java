package ir.iot.ubiqueui.service;

import java.util.ArrayList;
import java.util.List;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.device.MqttDevice;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.message.Message;
import ir.iot.ubique.common.service.api.DBService;
import ir.iot.ubique.common.utility.DataBaseFactory;
import ir.iot.ubiqueui.service.api.UiService;

public class UiServiceImpl implements UiService {

  @Override
  public void addDevice(String name, String url, String topic) throws DomainException {
    try {
      MqttDevice device = new MqttDevice();
      device.setName(name);
      device.setTopic(topic);
      device.setUrl(url);

      DBService dbService = DataBaseFactory.getInstance().getDataAccessService();
      dbService.saveOrUpdateDevice(device);

    } catch (DomainException e) {
      e.printStackTrace();
    }
  }

  @Override
  public List<Device> getDevices() throws DomainException {
    List<Device> devices = new ArrayList<Device>();
    try {
      DBService dbService = DataBaseFactory.getInstance().getDataAccessService();
      devices = dbService.listDevice();

    } catch (DomainException e) {
      e.printStackTrace();
    }
    return devices;

  }

  @Override
  public List<Message> getMessages(String topic) throws DomainException {
    List<Message> messages = new ArrayList<Message>();
    try {
      DBService dbService = DataBaseFactory.getInstance().getDataAccessService();
      messages = dbService.ListMessage(topic);

    } catch (DomainException e) {
      e.printStackTrace();
    }
    return messages;

  }

}
