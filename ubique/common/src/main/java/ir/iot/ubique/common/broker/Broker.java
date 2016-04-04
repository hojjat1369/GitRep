package ir.iot.ubique.common.broker;

import ir.iot.ubique.common.utility.AbstractEntity;

public class Broker extends AbstractEntity {

  private static final long serialVersionUID = 6950126319797990819L;

  protected String name;
  protected String url;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void doProcess() {

  }
}
