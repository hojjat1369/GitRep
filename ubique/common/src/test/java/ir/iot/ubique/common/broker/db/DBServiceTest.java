package ir.iot.ubique.common.broker.db;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import ir.iot.ubique.common.device.MqttDevice;
import ir.iot.ubique.common.exception.DomainException;
import ir.iot.ubique.common.message.Message;
import ir.iot.ubique.common.message.MqttMessage;
import ir.iot.ubique.common.service.api.DBService;
import ir.iot.ubique.common.utility.DataBaseFactory;

public class DBServiceTest {

  @Test
  public void testInsertMessage() {
    MqttMessage msg = new MqttMessage();
    msg.setMessage("hh");
    msg.setQos(1);
    msg.setTopic("topicName");

    DBService service = DataBaseFactory.getInstance().getDataAccessService();
    try {
      service.saveOrUpdateMessage(msg);
    } catch (DomainException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testGetMessage() {
    String topic = "topicName";

    DBService service = DataBaseFactory.getInstance().getDataAccessService();
    try {
      List<Message> msgs = service.ListMessage(topic);
      System.out.println(Arrays.toString(msgs.toArray()));
    } catch (DomainException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void testInsertDevice() {
    MqttDevice device = new MqttDevice();
    device.setDescription("Description");
    device.setName("name");
    device.setTags("tags");
    device.setUrl("localhost");
    device.setTopic("topicName");

    DBService service = DataBaseFactory.getInstance().getDataAccessService();
    try {
      service.saveOrUpdateDevice(device);
    } catch (DomainException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
