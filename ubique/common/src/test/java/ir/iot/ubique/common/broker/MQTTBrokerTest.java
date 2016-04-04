package ir.iot.ubique.common.broker;

import java.util.Arrays;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.junit.Test;

public class MQTTBrokerTest {

  MqttClient client;

  public static void main(String[] args) {

    String topic = "MQTT Examples";
    String content = "Message from MqttPublishSample";
    int qos = 2;
    String broker = "tcp://iot.eclipse.org:1883";
    String clientId = "JavaSample";
    MemoryPersistence persistence = new MemoryPersistence();

    try {
      MqttClient mqtt = new MqttClient(broker, clientId, persistence);
      MqttConnectOptions connOpts = new MqttConnectOptions();
      connOpts.setCleanSession(true);
      System.out.println("Connecting to broker: " + broker);
      mqtt.connect(connOpts);
      System.out.println("Connected");
      System.out.println("Publishing message: " + content);
      MqttMessage message = new MqttMessage(content.getBytes());
      message.setQos(qos);
      mqtt.subscribe(topic);
      System.out.println("Message published");
      mqtt.disconnect();
      System.out.println("Disconnected");
      System.exit(0);
    } catch (MqttException me) {
      System.out.println("reason " + me.getReasonCode());
      System.out.println("msg " + me.getMessage());
      System.out.println("loc " + me.getLocalizedMessage());
      System.out.println("cause " + me.getCause());
      System.out.println("excep " + me);
      me.printStackTrace();
    }
  }

  @Test
  public void testMQTT() {
    try {
      MQTTBrokerTest t = new MQTTBrokerTest();
      // t.doDemo();
      MqttClient client = new MqttClient("tcp://localhost:1883", // URI
          MqttClient.generateClientId(), // ClientId
          new MemoryPersistence());

      client.connect();

      MqttConnectOptions options = new MqttConnectOptions();

      // options.setMqttVersion(MqttConnectOptions.KEEP_ALIVE_INTERVAL_DEFAULT);
      // options.setMqttVersion(MqttConnectOptions.MQTT_VERSION_3_1_1);

      // client.connect(options);

      client.setCallback(new MqttCallback() {

        @Override
        public void connectionLost(Throwable cause) { // Called when the client lost the connection
                                                      // to the broker
        }

        @Override
        public void messageArrived(String topic, MqttMessage message) throws Exception {
          System.out.println(topic + ": " + Arrays.toString(message.getPayload()));
        }

        @Override
        public void deliveryComplete(IMqttDeliveryToken token) {// Called when a outgoing publish is
                                                                // complete
        }
      });

      client.subscribe("pahodemo/test", 1);

      client.disconnect();
      // Thread.sleep(50000);


    } catch (MqttException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } // Persistence
  }

  @Test
  public void doDemo() {
    try {
      client = new MqttClient("tcp://localhost:1883", "pahomqttpublish1");
      client.connect();
      MqttMessage message = new MqttMessage();
      message.setPayload("A single message".getBytes());
      client.publish("pahodemo/test", message);
      client.disconnect();
    } catch (MqttException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testSubBroker() {
    BrokerSubscriber bs = new BrokerSubscriber();
    bs.setDomain("hoj");
    bs.setThing("hoj");
    bs.setStuff("hoj");
    bs.setName("name");
    bs.setUrl("tcp://localhost:1885");
    bs.doProcess();
  }

  @Test
  public void testPubBroker() {
    // while (true) {
    BrokerPublisher bs = new BrokerPublisher();
    bs.setName("name");
    bs.setDomain("hoj");
    bs.setThing("hoj");
    bs.setStuff("hoj");
    // bs.setUsername("123");
    // bs.setPassword("123");
    bs.setUrl("tcp://localhost:1885");
    bs.doProcess();
    // }
  }
}
