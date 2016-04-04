/*
 * package ir.fanap.point.util;
 * 
 * import ir.fanap.point.domain.exception.database.DBMappingException;
 * 
 * import org.apache.log4j.Logger; import org.hibernate.FlushMode; import
 * org.hibernate.HibernateException; import org.hibernate.MappingException; import
 * org.hibernate.Session; import org.hibernate.SessionFactory; import
 * org.hibernate.cfg.AnnotationConfiguration; import org.hibernate.cfg.Configuration;
 * 
 * 
 * public class HibernateUtil {
 * 
 * private static SessionFactory sessionFactory = null; private static Configuration configuration =
 * null; private static Logger logger = Logger.getLogger(HibernateUtil.class); static { try {
 * refreshDB(); } catch (DBMappingException e) { logger.error(e.getMessage()); } }
 * 
 * public static SessionFactory getSessionFactory(){ return sessionFactory; }
 * 
 * public static void refreshDB() throws DBMappingException { System.gc(); try{ logger.info(
 * "Application Running Mode = PRODUCTION"); configuration = new
 * AnnotationConfiguration().configure("point.hibernate.cfg.xml"); initializeHibernate();
 * sessionFactory = configuration.buildSessionFactory(); }catch(MappingException e){
 * logger.error("", e); throw new DBMappingException(e); }catch(Exception e){ logger.error("", e);
 * throw new RuntimeException(e); } }
 * 
 * private static void initializeHibernate() { if(configuration != null) { if
 * (ConfigHandler.getInstance().getMessage("hibernate.connection.url") != null)
 * configuration.setProperty("hibernate.connection.url",
 * ConfigHandler.getInstance().getMessage("hibernate.connection.url")); if
 * (ConfigHandler.getInstance().getMessage("hibernate.connection.username") != null)
 * configuration.setProperty("hibernate.connection.username",
 * ConfigHandler.getInstance().getMessage("hibernate.connection.username")); if
 * (ConfigHandler.getInstance().getMessage("hibernate.connection.password") != null)
 * configuration.setProperty("hibernate.connection.password",
 * ConfigHandler.getInstance().getMessage("hibernate.connection.password")); if
 * (ConfigHandler.getInstance().getMessage("hibernate.connection.provider_class") != null)
 * configuration.setProperty("hibernate.connection.provider_class",
 * ConfigHandler.getInstance().getMessage("hibernate.connection.provider_class")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.connection.provider_class") != null) //
 * configuration.setProperty("hibernate.connection.provider_class",
 * ConfigHandler.getInstance().getMessage("hibernate.connection.provider_class")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.dialect") != null) //
 * configuration.setProperty("hibernate.dialect",
 * ConfigHandler.getInstance().getMessage("hibernate.dialect")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.hbm2ddl.auto") != null) //
 * configuration.setProperty("hibernate.hbm2ddl.auto",
 * ConfigHandler.getInstance().getMessage("hibernate.hbm2ddl.auto")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.current_session_context_class") != null) //
 * configuration.setProperty("hibernate.current_session_context_class",
 * ConfigHandler.getInstance().getMessage("hibernate.current_session_context_class")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.show_sql") != null) //
 * configuration.setProperty("hibernate.show_sql",
 * ConfigHandler.getInstance().getMessage("hibernate.show_sql")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.use_sql_comments") != null) //
 * configuration.setProperty("hibernate.use_sql_comments",
 * ConfigHandler.getInstance().getMessage("hibernate.use_sql_comments")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.format_sql") != null) //
 * configuration.setProperty("hibernate.format_sql",
 * ConfigHandler.getInstance().getMessage("hibernate.format_sql")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.use_outer_join") != null) //
 * configuration.setProperty("hibernate.use_outer_join",ConfigHandler.getInstance().getMessage(
 * "hibernate.use_outer_join")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.generate_statistics") != null) //
 * configuration.setProperty("hibernate.generate_statistics",
 * ConfigHandler.getInstance().getMessage("hibernate.generate_statistics")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.jdbc.fetch_size") != null) //
 * configuration.setProperty("hibernate.jdbc.fetch_size",ConfigHandler.getInstance().getMessage(
 * "hibernate.jdbc.fetch_size")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.jdbc.batch_size") != null) //
 * configuration.setProperty("hibernate.jdbc.batch_size",ConfigHandler.getInstance().getMessage(
 * "hibernate.jdbc.batch_size")); // if
 * (ConfigHandler.getInstance().getMessage("hibernate.c3p0.min_size") != null) //
 * configuration.setProperty("hibernate.c3p0.min_size",ConfigHandler.getInstance().getMessage(
 * "hibernate.c3p0.min_size")); // if (ConfigHandler.getInstance().getMessage( //
 * "hibernate.c3p0.max_size") != null) // configuration.setProperty( // "hibernate.c3p0.max_size",
 * // ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.max_size")); // if
 * (ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.timeout") != null) //
 * configuration.setProperty( // "hibernate.c3p0.timeout", //
 * ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.timeout")); // if
 * (ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.max_statements") != null) //
 * configuration.setProperty( // "hibernate.c3p0.max_statements", //
 * ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.max_statements")); // if
 * (ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.idle_test_period") != null) //
 * configuration.setProperty( // "hibernate.c3p0.idle_test_period", //
 * ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.idle_test_period")); // if
 * (ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.acquire_increment") != null) //
 * configuration.setProperty( // "hibernate.c3p0.acquire_increment", //
 * ConfigHandler.getInstance().getMessage( // "hibernate.c3p0.acquire_increment"));
 * 
 * // if (ConfigHandler.getInstance().getMessage( // "hibernate.cache.use_query_cache") != null) //
 * configuration.setProperty( // "hibernate.cache.use_query_cache", //
 * ConfigHandler.getInstance().getMessage( // "hibernate.cache.use_query_cache")); // if
 * (ConfigHandler.getInstance().getMessage( // "hibernate.cache.use_second_level_cache") != null) //
 * configuration.setProperty( // "hibernate.cache.use_second_level_cache", //
 * ConfigHandler.getInstance().getMessage( // "hibernate.cache.use_second_level_cache")); } } public
 * static void refreshDB(String configFile) throws DBMappingException{ Configuration configuration;
 * configuration = new AnnotationConfiguration().configure(configFile); try{ sessionFactory =
 * configuration.buildSessionFactory(); }catch(MappingException e){ throw new DBMappingException(e);
 * }
 * 
 * }
 * 
 * public static final ThreadLocal<Session> session = new ThreadLocal<Session>();
 * 
 * public static Session currentSession() throws HibernateException { Session s = session.get(); if
 * (s == null || !s.isOpen()) { s = sessionFactory.openSession(); }
 * s.setFlushMode(FlushMode.COMMIT); session.set(s); return s; }
 * 
 * public static Configuration getConfiguration() { return configuration; }
 * 
 * public static Session getSession() throws HibernateException { return currentSession(); }
 * 
 * public static void closeSession() throws HibernateException { Session s = session.get();
 * session.set(null); if (s != null) { if (s.isOpen()) { // s.flush(); // s.clear(); s.close(); //
 * s.disconnect(); } } }
 * 
 * @Deprecated public static void clearCache() { Session s = session.get(); if (s != null &&
 * s.isOpen()) s.clear(); sessionFactory.evictQueries(); }
 * 
 * @Deprecated public static void clearObject(Object obj) { Session s = session.get(); if (s != null
 * && s.isOpen()) s.evict(obj); sessionFactory.evict(obj.getClass()); } }
 */
package ir.iot.ubique.common.utility;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

  private static SessionFactory sessionFactory = null;

  private static Logger logger = Logger.getLogger(HibernateUtil.class);
  private static Configuration configuration = null;

  public static final ThreadLocal<Session> session = new ThreadLocal<Session>();

  static {
    try {
      refreshDB();
    } catch (Exception e) {
      logger.error("", e);
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void refreshDB() throws MappingException {
    System.gc();
    try {
      logger.info("Application Running Mode = PRODUCTION");
      String dataBaseRunningMode =
          EnvironmentPropertyHandler.getInstance().getProperty(ServiceKey.DB_RUNNING_MODE);
      String hibernatePath = "test-hibernate.cfg.xml";
      if (dataBaseRunningMode.equalsIgnoreCase(ServiceKey.PRODUCTION))
        hibernatePath = "hibernate.cfg.xml";
      configuration = new Configuration().configure(hibernatePath);
      sessionFactory = configuration.buildSessionFactory();
    } catch (MappingException e) {
      logger.error("", e);
      throw e;
    } catch (Exception e) {
      e.printStackTrace();
      logger.error("", e);
      throw new RuntimeException(e);
    }
  }

  public static Session getSession() throws HibernateException {
    Session s = session.get();
    if (s == null || !s.isOpen()) {
      s = sessionFactory.openSession();
    }
    s.setFlushMode(FlushMode.COMMIT);
    session.set(s);
    return s;
  }

  public static Session getManualSession() throws HibernateException {
    Session s = session.get();
    if (s == null || !s.isOpen()) {
      s = sessionFactory.openSession();
    }
    s.setFlushMode(FlushMode.MANUAL);
    session.set(s);
    return s;
  }

  public static void closeSession() throws HibernateException {
    Session s = session.get();
    session.set(null);
    if (s != null)
      if (s.isOpen())
        s.close();
  }


  public static void beginTx() {
    if (getSession() != null) {
      Transaction tx = getSession().getTransaction();
      tx.begin();
    }
  }

  public static void main(String[] args) {
    HibernateUtil.getSession();
  }

}
