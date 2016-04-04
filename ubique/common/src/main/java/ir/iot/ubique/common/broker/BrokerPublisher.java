package ir.iot.ubique.common.broker;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class BrokerPublisher extends MQTTBroker {

  private static final long serialVersionUID = 6950126319797990819L;

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
      // myClient.setCallback(this);
      myClient.connect(connOpt);
    } catch (MqttException e) {
      e.printStackTrace();
      System.exit(-1);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(-1);
    }

    System.out.println("Connected to " + this.url);

    // setup topic
    // String myTopic = "#";
    MqttTopic topic = myClient.getTopic(this.getTopic());

    // subscribe to topic if subscriber

    // publish messages if publisher
    for (int i = 1; i <= 10; i++) {
      String pubMsg = "{\"pubmsg\":" + i + "}";
      int pubQoS = 0;
      MqttMessage message = new MqttMessage(pubMsg.getBytes());
      message.setQos(pubQoS);
      message.setRetained(false);

      // Publish the message
      System.out.println("Publishing to topic \"" + topic + "\" qos " + pubQoS);
      MqttDeliveryToken token = null;
      try {
        // publish message to broker
        token = topic.publish(message);
        // Wait until the message has been delivered to the broker
        token.waitForCompletion();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // disconnect
    try {
      myClient.disconnect();
      Thread.sleep(100);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
