package ir.iot.ubique.common.broker;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttTopic;

public class SimpleMqttClient implements MqttCallback {

  MqttClient myClient;
  MqttConnectOptions connOpt;

  // static final String BROKER_URL = "tcp://q.m2m.io:1883";
  static final String BROKER_URL = "tcp://localhost:1883";
  static final String M2MIO_DOMAIN = "hoj";
  static final String M2MIO_STUFF = "things";
  static final String M2MIO_THING = "hoj";
  static final String M2MIO_USERNAME = "123";
  static final String M2MIO_PASSWORD_MD5 = "123";

  // the following two flags control whether this example is a publisher, a subscriber or both
  static final Boolean subscriber = true;
  static final Boolean publisher = true;

  /**
   * 
   * connectionLost This callback is invoked upon losing the MQTT connection.
   * 
   */
  @Override
  public void connectionLost(Throwable t) {
    System.out.println("Connection lost!");
    // code to reconnect to the broker would go here if desired
  }

  /**
   * 
   * deliveryComplete This callback is invoked when a message published by this client is
   * successfully received by the broker.
   * 
   */
  public void deliveryComplete(MqttDeliveryToken token) {
    // System.out.println("Pub complete" + new String(token.getMessage().getPayload()));
  }


  /**
   * 
   * MAIN
   * 
   */
  public static void main(String[] args) {
    SimpleMqttClient smc = new SimpleMqttClient();
    smc.runClient();
  }

  /**
   * 
   * runClient The main functionality of this simple example. Create a MQTT client, connect to
   * broker, pub/sub, disconnect.
   * 
   */
  public void runClient() {
    // setup MQTT Client
    String clientID = M2MIO_THING;
    connOpt = new MqttConnectOptions();

    connOpt.setCleanSession(true);
    connOpt.setKeepAliveInterval(30);
    connOpt.setUserName(M2MIO_USERNAME);
    connOpt.setPassword(M2MIO_PASSWORD_MD5.toCharArray());

    // Connect to Broker
    try {
      myClient = new MqttClient(BROKER_URL, clientID);
      myClient.setCallback(this);
      myClient.connect(connOpt);
    } catch (MqttException e) {
      e.printStackTrace();
      System.exit(-1);
    }

    System.out.println("Connected to " + BROKER_URL);

    // setup topic
    // topics on m2m.io are in the form <domain>/<stuff>/<thing>
    String myTopic = M2MIO_DOMAIN + "/" + M2MIO_STUFF + "/" + M2MIO_THING;
    // String myTopic = "#";
    MqttTopic topic = myClient.getTopic(myTopic);

    // subscribe to topic if subscriber
    if (subscriber) {
      try {
        int subQoS = 0;
        myClient.subscribe(myTopic, subQoS);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // publish messages if publisher
    if (publisher) {
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
          Thread.sleep(100);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }

    // disconnect
    try {
      // wait to ensure subscribed messages are delivered
      if (subscriber) {
        Thread.sleep(5000);
      }
      myClient.disconnect();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void deliveryComplete(IMqttDeliveryToken arg0) {
    // TODO Auto-generated method stub

  }

  /**
   * 
   * messageArrived This callback is invoked when a message is received on a subscribed topic.
   * 
   */
  @Override
  public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
    System.out.println("-------------------------------------------------");
    System.out.println("| Topic:" + arg0);
    System.out.println("| Message: " + new String(arg1.getPayload()));
    System.out.println("-------------------------------------------------");

  }
}
