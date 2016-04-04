package ir.iot.ubique.common.message;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import ir.iot.ubique.common.utility.AbstractEntity;

@Entity
@Table(name = "UBQ_MESSAGE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MESSAGE_TYPE", discriminatorType = DiscriminatorType.STRING)
public class Message extends AbstractEntity {

  private static final long serialVersionUID = 117731163919432475L;

}
