package ir.iot.ubique.common.utility;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.PropertyValueException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.SQLGrammarException;

import ir.iot.ubique.common.exception.db.DBConstraintViolationException;
import ir.iot.ubique.common.exception.db.DBGenericJDBCException;
import ir.iot.ubique.common.exception.db.DBNonUniqueException;
import ir.iot.ubique.common.exception.db.DBNonUniqueResultException;
import ir.iot.ubique.common.exception.db.DBPropertyValueException;
import ir.iot.ubique.common.exception.db.DBSQLGrammerException;


@SuppressWarnings("all")
public class GenericRepository<T> {

  private Session session;
  private Transaction tx;

  protected Class<T> type;

  public GenericRepository(Class<T> type) {
    this.type = type;
  }

  protected static Logger logger = Logger.getLogger(GenericRepository.class);



  public GenericRepository() {
    // HibernateUtil;
  }

  protected void startOperation() throws HibernateException {
    session = HibernateUtil.getSession();
    tx = session.beginTransaction();
  }

  public void save(T instance)
      throws DBNonUniqueException, DBConstraintViolationException, DBPropertyValueException {

    try {
      session = HibernateUtil.getSession();
      Transaction transaction = session.beginTransaction();
      session.save(instance);
      transaction.commit();
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    } catch (PropertyValueException e) {
      throw new DBPropertyValueException(e);
    }

  }

  public void saveOrUpdate(T instance)
      throws DBNonUniqueException, DBConstraintViolationException, DBPropertyValueException {

    try {
      startOperation();
      session.saveOrUpdate(instance);
      tx.commit();
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    } catch (PropertyValueException e) {
      throw new DBPropertyValueException(e);
    }
  }

  public void merge(T instance) throws DBNonUniqueException, DBConstraintViolationException {
    try {
      startOperation();
      session.merge(instance);
      tx.commit();
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    }
  }

  public T loadProxy(String id)
      throws DBNonUniqueException, DBConstraintViolationException, DBNonUniqueResultException {
    return loadProxy(id, LockMode.NONE);
  }

  /***
   * @caution be careful when using this method with lm!=LockMode.NONE
   * @param id
   * @param lm
   * @return
   * @throws DBNonUniqueException
   * @throws DBConstraintViolationException
   * @throws DBNonUniqueResultException
   */
  public T loadProxy(String id, LockMode lm)
      throws DBNonUniqueException, DBConstraintViolationException, DBNonUniqueResultException {

    T ret = null;
    try {
      startOperation();
      Object o = session.load(type, id, lm);
      ret = type.cast(o);
      tx.commit();
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    } catch (NonUniqueResultException e) {
      throw new DBNonUniqueResultException(e);
    }
    return ret;
  }

  public T load(String id)
      throws DBNonUniqueResultException, DBNonUniqueException, DBConstraintViolationException {
    return load(id, LockMode.NONE);
  }

  public T load(Long id)
      throws DBNonUniqueResultException, DBNonUniqueException, DBConstraintViolationException {
    return load(id, LockMode.NONE);
  }

  /***
   * @caution be careful when using this method with lm!=LockMode.NONE!
   * @param id
   * @param lm
   * @return
   * @throws DBNonUniqueResultException
   * @throws DBNonUniqueException
   * @throws DBConstraintViolationException
   */
  public T load(Long id, LockMode lm)
      throws DBNonUniqueResultException, DBNonUniqueException, DBConstraintViolationException {

    T ret = null;
    try {
      startOperation();
      Object o = session.get(type, id, lm);
      ret = type.cast(o);
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    } catch (NonUniqueResultException e) {
      throw new DBNonUniqueResultException(e);
    }
    return ret;
  }

  /***
   * @caution be careful when using this method with lm!=LockMode.NONE!
   * @param id
   * @param lm
   * @return
   * @throws DBNonUniqueResultException
   * @throws DBNonUniqueException
   * @throws DBConstraintViolationException
   */
  public T load(String id, LockMode lm)
      throws DBNonUniqueResultException, DBNonUniqueException, DBConstraintViolationException {

    T ret = null;
    try {
      startOperation();
      Object o = session.get(type, id, lm);
      ret = type.cast(o);
      tx.commit();
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    } catch (NonUniqueResultException e) {
      throw new DBNonUniqueResultException(e);
    }
    return ret;
  }

  public void update(T transientObject) throws DBNonUniqueResultException, DBNonUniqueException,
      DBConstraintViolationException, DBPropertyValueException {

    try {
      startOperation();
      session.update(transientObject);
      tx.commit();
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    } catch (PropertyValueException e) {
      throw new DBPropertyValueException(e);
    }
  }

  public void update(String id) throws DBNonUniqueException, DBConstraintViolationException,
      DBNonUniqueResultException, DBPropertyValueException {

    try {
      T temp = loadProxy(id);
      update(temp);
    } catch (ConstraintViolationException e) {
      throw new DBConstraintViolationException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    }

  }

  public void delete(T persistentObject) throws DBNonUniqueResultException, DBNonUniqueException {

    try {
      startOperation();
      session.evict(persistentObject);
      session.delete(persistentObject);
      tx.commit();
    } catch (ConstraintViolationException e) {
      throw new DBNonUniqueResultException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    }

  }

  public void delete(String id)
      throws DBNonUniqueResultException, DBNonUniqueException, DBConstraintViolationException {

    try {
      startOperation();
      T temp = loadProxy(id);
      delete(temp);
      tx.commit();
    } catch (ConstraintViolationException e) {
      throw new DBNonUniqueResultException(e);
    } catch (NonUniqueObjectException e) {
      throw new DBNonUniqueException(e);
    }
  }

  public List<T> listAll(int begin, int length)
      throws DBSQLGrammerException, DBGenericJDBCException {
    Query query = session.createQuery("from " + type.getName());
    try {
      return query.list();
    } catch (GenericJDBCException e) {
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      throw new DBSQLGrammerException(e);
    }
  }

  public List<T> listAll(int begin, int length, String sortColumn, Boolean sortValue)
      throws DBSQLGrammerException, DBGenericJDBCException {
    QueryManager<T> qm = new QueryManager<T>();
    StringBuilder query = new StringBuilder("select o from " + type.getName() + " o ");
    if (sortColumn != null && !sortColumn.isEmpty()) {
      query.append(" order by o.").append(sortColumn);
      query.append(sortValue ? " asc " : " desc ");
    }
    try {
      return qm.executeQueryPaged(query.toString(), begin, length);

    } catch (GenericJDBCException e) {
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      throw new DBSQLGrammerException(e);
    }
  }

  public Map<String, T> loadAll(List<String> ids)
      throws DBGenericJDBCException, DBSQLGrammerException {
    QueryManager<T> qm = new QueryManager<T>();
    if (ids == null || ids.size() < 1)
      return null;

    StringBuilder query = new StringBuilder("select o from " + type.getName() + " o ");
    query.append(" where o.id in (");
    for (int i = 0; i < ids.size(); i++) {
      if (ids.get(i) != null && !ids.get(i).isEmpty()) {
        query.append("'");
        query.append(ids.get(i));
        query.append("',");
      }
    }

    if (query.charAt(query.length() - 1) == ',')
      query.deleteCharAt(query.length() - 1);

    query.append(")");

    try {

      List<T> list = qm.executeQuery(query.toString());
      Map<String, T> res = new HashMap<String, T>();
      Method method = type.getMethod("getId");
      for (int i = 0; i < list.size(); i++) {
        String id = (String) method.invoke(list.get(i));
        logger.debug("id is: " + id);
        res.put(id, list.get(i));
      }

      return res;
    } catch (GenericJDBCException e) {
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      throw new DBSQLGrammerException(e);
    } catch (Exception e) {
      throw new DBGenericJDBCException(e);
    }
  }

  public T loadAndLock(String id)
      throws DBNonUniqueResultException, DBNonUniqueException, DBConstraintViolationException {
    return load(id, LockMode.UPGRADE_NOWAIT);
  }

  public T getAndLock(String id)
      throws DBGenericJDBCException, DBSQLGrammerException, DBNonUniqueResultException {
    try {
      QueryManager<T> qm = new QueryManager<T>();
      StringBuilder query =
          new StringBuilder(String.format("select o from %s o where o.id = ? ", type.getName()));
      return qm.executeQueryUniqueResult(LockMode.UPGRADE_NOWAIT, query.toString(), id);
    } catch (GenericJDBCException e) {
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      throw new DBSQLGrammerException(e);
    } catch (Exception e) {
      throw new DBGenericJDBCException(e);
    }
  }

  public T getAndLock(String id, int timeout)
      throws DBGenericJDBCException, DBSQLGrammerException, DBNonUniqueResultException {
    try {
      QueryManager<T> qm = new QueryManager<T>();
      StringBuilder query =
          new StringBuilder(String.format("select o from %s o where o.id = ? ", type.getName()));
      return qm.executeQueryUniqueResult(LockMode.UPGRADE_NOWAIT, timeout, query.toString(), id);
    } catch (GenericJDBCException e) {
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      throw new DBSQLGrammerException(e);
    } catch (Exception e) {
      throw new DBGenericJDBCException(e);
    }
  }

  public T getWaited(String id, int timeout)
      throws DBGenericJDBCException, DBSQLGrammerException, DBNonUniqueResultException {
    try {
      QueryManager<T> qm = new QueryManager<T>();
      StringBuilder query =
          new StringBuilder(String.format("select o from %s o where o.id = ? ", type.getName()));
      return qm.executeQueryUniqueResult(timeout, query.toString(), id);
    } catch (GenericJDBCException e) {
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      throw new DBSQLGrammerException(e);
    } catch (Exception e) {
      throw new DBGenericJDBCException(e);
    }
  }
}
