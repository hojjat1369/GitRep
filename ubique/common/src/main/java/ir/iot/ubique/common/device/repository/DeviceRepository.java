package ir.iot.ubique.common.device.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ir.iot.ubique.common.device.Device;
import ir.iot.ubique.common.exception.db.DBException;
import ir.iot.ubique.common.exception.db.DBGenericJDBCException;
import ir.iot.ubique.common.exception.db.DBSQLGrammerException;
import ir.iot.ubique.common.utility.GenericRepository;
import ir.iot.ubique.common.utility.HibernateUtil;
import ir.iot.ubique.common.utility.QueryManager;

public class DeviceRepository extends GenericRepository<Device> {

  // Logger logger = Logger.getLogger(DeviceRepository.class);

  public DeviceRepository() {
    super(Device.class);
  }

  public Long saveOrUpdateDevice(Device device) throws DBException {

    long id;
    try {
      Session session = HibernateUtil.getSession();
      Transaction transaction = session.beginTransaction();
      transaction.begin();
      session.saveOrUpdate(device);
      transaction.commit();
      id = device.getId();

    } catch (HibernateException e) {
      // logger.error("", e);
      throw new DBException(e.getMessage());
    }
    return id;
  }

  public List<Device> listDevice() throws DBException {

    QueryManager<Device> qm = new QueryManager<>();
    StringBuilder qs = new StringBuilder();
    List<Object> values = new ArrayList<>();
    qs.append("from Device ad ");

    List<Device> accountDetailList = new ArrayList<Device>();
    try {
      accountDetailList = qm.executeQuery(qs.toString(), values.toArray());
    } catch (DBGenericJDBCException | DBSQLGrammerException e) {
      // logger.error("", e);
      throw new DBException(e.getMessage());
    }

    return accountDetailList;
  }

}
