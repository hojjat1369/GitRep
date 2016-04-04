package ir.iot.ubique.common.broker;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import ir.iot.ubique.common.service.api.DBService;
import ir.iot.ubique.common.utility.DataBaseFactory;

public class BrokerSubscriber extends MQTTBroker implements MqttCallback {

  private static final long serialVersionUID = 3391535913366391784L;

  private String payLoad;
  private int qos;
  private MqttMessage mqttMessage;

  @Override
  public void connectionLost(Throwable arg0) {
    System.out.println("Connection lost!");
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken arg0) {

  }

  @Override
  public void messageArrived(String topic, MqttMessage arg1) throws Exception {
    this.payLoad = new String(arg1.getPayload());
    this.qos = arg1.getQos();

    ir.iot.ubique.common.message.MqttMessage message =
        new ir.iot.ubique.common.message.MqttMessage();
    message.setMessage(this.payLoad);
    message.setQos(this.qos);
    message.setTopic(topic);

    DBService service = DataBaseFactory.getInstance().getDataAccessService();
    service.saveOrUpdateMessage(message);

    // System.out.println("-------------------------------------------------");
    // System.out.println("| Topic:" + topic);
    // System.out.println("| Message: " + new String(arg1.getPayload()));
    // System.out.println("-------------------------------------------------");

  }

  public void doProcess() {

    MqttClient myClient = null;
    MqttConnectOptions connOpt;

    // setup MQTT Client
    String clientID = this.domain;
    connOpt = new MqttConnectOptions();

    connOpt.setCleanSession(true);
    connOpt.setKeepAliveInterval(30);
    // connOpt.setUserName(this.username);
    // connOpt.setPassword(this.password.toCharArray());

    // Connect to Broker
    try {
      myClient = new MqttClient(this.url, clientID);
      myClient.setCallback(this);
      myClient.connect(connOpt);
    } catch (MqttException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    System.out.println("Connected to " + this.url);

    // subscribe to topic if subscriber
    try {
      int subQoS = 0;
      myClient.subscribe(this.getTopic(), subQoS);
      // Thread.sleep(50000);

      // disconnect
      // myClient.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getPayLoad() {
    return payLoad;
  }

  public void setPayLoad(String payLoad) {
    this.payLoad = payLoad;
  }

  public int getQos() {
    return qos;
  }

  public void setQos(int qos) {
    this.qos = qos;
  }

  public MqttMessage getMqttMessage() {
    return mqttMessage;
  }

  public void setMqttMessage(MqttMessage mqttMessage) {
    this.mqttMessage = mqttMessage;
  }

}
