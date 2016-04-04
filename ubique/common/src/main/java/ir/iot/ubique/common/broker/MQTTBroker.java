package ir.iot.ubique.common.broker;

public class MQTTBroker extends Broker {

  private static final long serialVersionUID = 6950126319797990819L;

  protected String domain;
  protected String stuff;
  protected String thing;
  protected String username;
  protected String password;

  @Override
  public void doProcess() {

  }

  public String getTopic() {
    // setup topic
    // topics on m2m.io are in the form <domain>/<stuff>/<thing>
    String myTopic = domain + "/" + stuff + "/" + thing;
    return myTopic;
  }

  public String getDomain() {
    return domain;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public String getStuff() {
    return stuff;
  }

  public void setStuff(String stuff) {
    this.stuff = stuff;
  }

  public String getThing() {
    return thing;
  }

  public void setThing(String thing) {
    this.thing = thing;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
