package ir.iot.ubique.common.message.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.iot.ubique.common.exception.db.DBException;
import ir.iot.ubique.common.exception.db.DBGenericJDBCException;
import ir.iot.ubique.common.exception.db.DBSQLGrammerException;
import ir.iot.ubique.common.message.Message;
import ir.iot.ubique.common.utility.GenericRepository;
import ir.iot.ubique.common.utility.HibernateUtil;
import ir.iot.ubique.common.utility.QueryManager;

public class MessageRepository extends GenericRepository<Message> {

  // Logger logger = Logger.getLogger(MessageRepository.class);

  public MessageRepository() {
    super(Message.class);
  }

  public Long saveOrUpdateMessage(Message message) throws DBException {

    Long id = null;
    try {
      Session session = HibernateUtil.getSession();
      Transaction transaction = session.beginTransaction();
      transaction.begin();
      session.saveOrUpdate(message);
      transaction.commit();
      id = message.getId();
    } catch (HibernateException e) {
      // logger.error("", e);
      throw new DBException(e.getMessage());
    }
    return id;
  }

  public List<Message> listMessage(String topic) throws DBException {

    QueryManager<Message> qm = new QueryManager<>();
    StringBuilder qs = new StringBuilder();
    List<Object> values = new ArrayList<>();
    qs.append("from MqttMessage ad where ad.topic = ?");

    values.add(topic);

    List<Message> messages = new ArrayList<Message>();
    try {
      messages = qm.executeQuery(qs.toString(), values.toArray());
    } catch (DBGenericJDBCException | DBSQLGrammerException e) {
      // logger.error("", e);
      throw new DBException(e.getMessage());
    }

    return messages;
  }

}
