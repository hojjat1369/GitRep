package ir.iot.ubique.common.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.CacheMode;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.QueryException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.exception.LockAcquisitionException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.transform.Transformers;

import ir.iot.ubique.common.exception.db.DBGenericJDBCException;
import ir.iot.ubique.common.exception.db.DBNonUniqueException;
import ir.iot.ubique.common.exception.db.DBNonUniqueResultException;
import ir.iot.ubique.common.exception.db.DBSQLGrammerException;

@SuppressWarnings("unchecked")
public class QueryManager<T> {
  private final String QUERY_CACHE_REGION = "QUERY_REGION";
  private static final Logger logger = Logger.getLogger(QueryManager.class);

  private Query makeQuery(String query, Object... values) {
    Session session = HibernateUtil.getSession();
    Query q = session.createQuery(query);
    if (values != null)
      for (int i = 0; i < values.length; i++) {
        if (values[i] == null)
          q.setParameter(i, null);
        else if (values[i].getClass().getSimpleName().equals("Long"))
          q.setLong(i, (Long) values[i]);
        else if (values[i].getClass().getSimpleName().equals("String"))
          q.setString(i, (String) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Integer"))
          q.setInteger(i, (Integer) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Double"))
          q.setDouble(i, (Double) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Boolean"))
          q.setBoolean(i, (Boolean) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Date"))
          q.setDate(i, (java.util.Date) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Timestamp")) {
          q.setTimestamp(i, (Timestamp) values[i]);
        } else
          q.setEntity(i, values[i]);
      }
    logger.debug("query to execute is: " + q);
    return q;
  }

  private Query makeSQLQuery(String query, Object... values) {
    Session session = HibernateUtil.getSession();
    Query q = session.createSQLQuery(query);
    if (values != null)
      for (int i = 0; i < values.length; i++) {
        if (values[i] == null)
          q.setParameter(i, null);
        else if (values[i].getClass().getSimpleName().equals("Long"))
          q.setLong(i, (Long) values[i]);
        else if (values[i].getClass().getSimpleName().equals("String"))
          q.setString(i, (String) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Integer"))
          q.setInteger(i, (Integer) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Double"))
          q.setDouble(i, (Double) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Boolean"))
          q.setBoolean(i, (Boolean) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Date"))
          q.setDate(i, (java.util.Date) values[i]);
        else if (values[i].getClass().getSimpleName().equals("Timestamp"))
          q.setTimestamp(i, (Timestamp) values[i]);
        else
          q.setEntity(i, values[i]);
      }
    q.setCacheRegion(QUERY_CACHE_REGION);
    q.setCacheMode(CacheMode.NORMAL);
    logger.debug(q);
    return q;

  }

  public void delete(String query, Object... values)
      throws DBNonUniqueResultException, DBNonUniqueException {
    try {
      Query q = makeQuery(query, values);
      q.executeUpdate();
    } catch (ConstraintViolationException e) {
      logger.error(e);
      throw new DBNonUniqueResultException(e);
    } catch (NonUniqueObjectException e) {
      logger.error(e);
      throw new DBNonUniqueException(e);
    }
  }

  public List<T> executeQuery(String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException {
    List<Object> ret;
    List<T> result;
    try {
      Query q = makeQuery(query, values);
      logger.debug("query done " + query);
      ret = q.list();
      result = makeClass(ret);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    } catch (Exception e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
    logger.debug("result.size: " + result.size());
    return result;
  }



  public List<T> executeQuery(LockMode lm, String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException {
    List<Object> ret = null;
    List<T> result;
    try {
      Query q = makeQuery(query, values);
      logger.debug("query done " + query);
      long cur = System.currentTimeMillis();
      long now = System.currentTimeMillis();
      do {
        try {
          ret = q.list();
          now = System.currentTimeMillis();
          break;
        } catch (LockAcquisitionException e) {
          logger.error(e);
        }
      } while (!lm.greaterThan(LockMode.UPGRADE_NOWAIT) && now - cur <= 10000);

      result = makeClass(ret);

    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    } catch (Exception e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
    logger.debug("result.size: " + result.size());
    return result;
  }


  private List<T> makeClass(List<Object> input) {
    List<T> ret = new ArrayList<T>();
    int size = 0;
    if (input != null)
      size = input.size();
    for (int i = 0; i < size; i++) {
      T curr = (T) input.get(i);
      ret.add(curr);
    }
    return ret;

  }

  public List<T> executeMaxResultQuery(int maxResult, String query, Object... values)
      throws GenericJDBCException, SQLGrammarException, QueryException, HibernateException,
      DBGenericJDBCException, DBSQLGrammerException {
    Session session = HibernateUtil.getSession();
    List<Object> ret;
    List<T> result;
    Query q = session.createQuery(query);
    Method[] methods = q.getClass().getMethods();
    HashMap<String, Method> setters = new HashMap<String, Method>();
    for (Method m : methods) {
      if (m.getName().startsWith("set") && m.getParameterTypes()[0] == int.class)
        setters.put(m.getName(), m);
    }

    for (int i = 0; i < values.length; i++) {
      try {
        Class<?> clazz = values[i].getClass();
        if (setters.containsKey("set" + clazz.getSimpleName()))
          setters.get("set" + clazz.getSimpleName()).invoke(q, i, clazz.cast(values[i]));
        else
          q.setEntity(i, values[i]);
      } catch (InvocationTargetException e) {
        logger.error(e);
      } catch (IllegalAccessException e) {
        logger.error(e);
      }
    }
    q.setCacheable(true);
    q.setMaxResults(maxResult);
    q.setCacheMode(CacheMode.NORMAL);
    q.setCacheRegion(QUERY_CACHE_REGION);
    try {
      ret = q.list();
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }

    result = makeClass(ret);
    logger.debug("result.size: " + result.size());
    return result;
  }

  public T executeQueryUniqueResult(String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException, DBNonUniqueResultException {
    return executeQueryUniqueResult(LockMode.NONE, query, values);
  }

  /**
   * @param lm
   * @param query
   * @param values
   * @return
   * @throws DBGenericJDBCException
   * @throws DBSQLGrammerException
   * @throws DBNonUniqueResultException
   * @caution be careful when using this method with lm!=LockMode.NONE!
   */
  public T executeQueryUniqueResult(LockMode lm, String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException, DBNonUniqueResultException {
    return executeQueryUniqueResult(lm, 10000, query, values);
  }

  /**
   * @param lm
   * @param query
   * @param values
   * @param timeoutMilis
   * @return
   * @throws DBGenericJDBCException
   * @throws DBSQLGrammerException
   * @throws DBNonUniqueResultException
   * @caution be careful when using this method with lm!=LockMode.NONE!
   */
  public T executeQueryUniqueResult(LockMode lm, int timeoutMilis, String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException, DBNonUniqueResultException {
    T ret = null;
    try {
      Query q = makeQuery(query, values);
      q.setLockMode("this", lm);

      long cur = System.currentTimeMillis();
      long now = System.currentTimeMillis();
      do {
        try {
          ret = (T) q.uniqueResult();
          now = System.currentTimeMillis();
          break;
        } catch (LockAcquisitionException e) {
          logger.trace(e);
        }
      } while (!lm.greaterThan(LockMode.UPGRADE_NOWAIT) && now - cur <= timeoutMilis);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    } catch (NonUniqueResultException e) {
      logger.error(e);
      throw new DBNonUniqueResultException(e);
    }
    logger.debug("unique result: " + ret);
    return ret;
  }

  /**
   * @param timeoutMilis
   * @param query
   * @param values
   * @return
   * @throws DBGenericJDBCException
   * @throws DBSQLGrammerException
   * @throws DBNonUniqueResultException
   * @caution be careful when using this method with lm!=LockMode.NONE!
   */
  public T executeQueryUniqueResult(int timeoutMilis, String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException, DBNonUniqueResultException {
    T ret = null;
    try {
      Query q = makeQuery(query, values);

      long cur = System.currentTimeMillis();
      long now = System.currentTimeMillis();
      do {
        ret = (T) q.uniqueResult();
        now = System.currentTimeMillis();
        break;
      } while (ret == null && now - cur <= timeoutMilis);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    } catch (NonUniqueResultException e) {
      logger.error(e);
      throw new DBNonUniqueResultException(e);
    }
    logger.debug("unique result: " + ret);
    return ret;
  }

  public List<T> executeQueryPaged(String query, int begin, int length, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException {

    return executeQueryPaged(LockMode.NONE, query, begin, length, values);
  }

  /***
   * @param lm
   * @param query
   * @param begin
   * @param length
   * @param values
   * @return
   * @throws DBGenericJDBCException
   * @throws DBSQLGrammerException
   * @caution be careful when using this method with lm!=LockMode.NONE
   */
  public List<T> executeQueryPaged(LockMode lm, String query, int begin, int length,
      Object... values) throws DBGenericJDBCException, DBSQLGrammerException {
    return executeQueryPaged(lm, 10000, query, begin, length, values);
  }

  /***
   * @param lm
   * @param query
   * @param begin
   * @param length
   * @param values
   * @return
   * @throws DBGenericJDBCException
   * @throws DBSQLGrammerException
   * @caution be careful when using this method with lm!=LockMode.NONE
   */
  public List<T> executeQueryPaged(LockMode lm, int timeoutMilis, String query, int begin,
      int length, Object... values) throws DBGenericJDBCException, DBSQLGrammerException {
    List<Object> ret = null;
    List<T> result;
    try {
      Query q = makeQuery(query, values);
      q.setLockMode("this", lm);
      if (begin >= 0 && length >= 0)
        q.setFirstResult(begin).setMaxResults(length);

      long cur = System.currentTimeMillis();
      long now = System.currentTimeMillis();
      do {
        try {
          ret = q.list();
          now = System.currentTimeMillis();
          break;
        } catch (LockAcquisitionException e) {
          logger.debug(e);
        }
      } while (!lm.greaterThan(LockMode.UPGRADE_NOWAIT) && now - cur <= timeoutMilis);

      result = makeClass(ret);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
    logger.debug("result.size: " + result.size());
    return result;
  }

  public List<T> executeSQLQueryPaged(String query, int begin, int length, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException {
    List<Object> ret;
    List<T> result;
    try {
      Query q = makeSQLQuery(query, values);
      q.setFirstResult(begin).setMaxResults(length);
      q.setCacheable(true);
      q.setCacheMode(CacheMode.NORMAL);
      q.setCacheRegion(QUERY_CACHE_REGION);
      ret = q.list();
      result = makeClass(ret);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
    logger.debug("result.size: " + result.size());
    return result;
  }

  public List<T> executeSQLQuery(String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException {
    List<Object> ret;
    List<T> result;
    try {
      Query q = makeSQLQuery(query, values);
      ret = q.list();
      result = makeClass(ret);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
    logger.debug("result.size: " + result.size());
    return result;
  }

  public T getEntityAtIndex(String query, int index, Object... values) {
    List<Object> ret;
    List<T> result;
    Query q = makeQuery(query, values);
    ret = q.setFirstResult(index).setMaxResults(1).list();
    result = makeClass(ret);

    if (result != null && result.size() > 0)
      return result.get(0);
    return null;

  }

  public void executeSQLUpdate(String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException {
    try {
      Query q = makeSQLQuery(query, values);
      q.executeUpdate();
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
  }

  public int executeUpdate(String query, Object... values)
      throws DBGenericJDBCException, DBSQLGrammerException {
    try {
      Query q = makeQuery(query, values);
      return q.executeUpdate();
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
  }

  public List<T> executeQueryWithTransformer(String query, Class<T> transformer, Object[] values)
      throws DBGenericJDBCException, DBSQLGrammerException {
    List<Object> ret;
    List<T> result;
    try {
      Query q = makeQuery(query, values);
      q.setResultTransformer(Transformers.aliasToBean(transformer));
      logger.debug("query done " + query);
      ret = q.list();
      result = makeClass(ret);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    } catch (Exception e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
    logger.debug("result.size: " + result.size());
    return result;
  }

  public List<T> executeQueryPagedWithTransformer(String query, Class<T> transformer, int begin,
      int length, Object... values) throws DBGenericJDBCException, DBSQLGrammerException {
    List<Object> ret = null;
    List<T> result;
    try {
      Query q = makeQuery(query, values);
      q.setResultTransformer(Transformers.aliasToBean(transformer));

      if (begin >= 0 && length >= 0)
        q.setFirstResult(begin).setMaxResults(length);

      ret = q.list();
      result = makeClass(ret);
    } catch (GenericJDBCException e) {
      logger.error(e);
      throw new DBGenericJDBCException(e);
    } catch (SQLGrammarException e) {
      logger.error(e);
      throw new DBSQLGrammerException(e);
    }
    logger.debug("result.size: " + result.size());
    return result;
  }

}
