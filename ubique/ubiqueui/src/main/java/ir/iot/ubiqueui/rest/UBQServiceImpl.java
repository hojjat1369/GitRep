package ir.iot.ubiqueui.rest;

import ir.iot.ubique.common.device.MqttDevice;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.message.JsonMessage;
import ir.iot.ubique.common.service.ServiceResult;
import ir.iot.ubique.common.service.api.DBService;
import ir.iot.ubique.common.utility.DataBaseFactory;
import ir.iot.ubiqueui.rest.api.UBQService;
import ir.iot.ubiqueui.rest.entity.JsonRequest;

public class UBQServiceImpl implements UBQService {

  public void defineDevice(String name, String url, String topic) throws DomainException {
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
  public ServiceResult addData(JsonRequest request) {
    ServiceResult serviceResult = new ServiceResult();

    try {

      JsonMessage message = new JsonMessage();
      message.setTopic(request.getTopic());
      message.setMessage(request.getData());

      DBService service = DataBaseFactory.getInstance().getDataAccessService();
      service.saveOrUpdateMessage(message);

      serviceResult.setSuccess(true);
      serviceResult.setMessage("successfull");

    } catch (DomainException e) {
      e.printStackTrace();
      serviceResult.setSuccess(true);
      serviceResult.setMessage("failed");
    }
    return serviceResult;
  }
}
