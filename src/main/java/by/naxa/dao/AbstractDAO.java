package by.naxa.dao;

import by.naxa.dao.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract DAO. Consists of typical transactions.
 * @see org.hibernate.Session
 * Created by phomal on 09.12.2014.
 */
public abstract class AbstractDAO {

	private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);

	private Session session;
	private Transaction transaction;

	protected AbstractDAO() {}

	protected void doSaveOrUpdate(Object obj) {
		logger.info("+doSaveOrUpdate()");
		try {
			startOperation();
			session.saveOrUpdate(obj);
			logger.info("doSaveOrUpdate(): saved");
			transaction.commit();
			logger.info("doSaveOrUpdate(): committed");
		} catch (HibernateException exc) {
			if (transaction != null) transaction.rollback();
			throw exc;
		} finally {
			session.close();
		}
		logger.info("-doSaveOrUpdate()");
	}

	protected void doMerge(Object obj) {
		logger.info("+doSaveOrUpdate()");
		try {
			startOperation();
			session.saveOrUpdate(obj);
			logger.info("doSaveOrUpdate(): saved");
			transaction.commit();
			logger.info("doSaveOrUpdate(): committed");
		} catch (HibernateException exc) {
			if (transaction != null) transaction.rollback();
			throw exc;
		} finally {
			session.close();
		}
		logger.info("-doSaveOrUpdate()");
	}

	protected void doDelete(Object obj) {
		try {
			startOperation();
			session.delete(obj);
			transaction.commit();
		} catch (HibernateException exc) {
			if (transaction != null) transaction.rollback();
			throw exc;
		} finally {
			session.close();
		}
	}

	/**
	 *
	 * @return a persistent instance or null
	 * @see org.hibernate.Session#get(Class, java.io.Serializable)
	 */
	protected Object doFind(Class clazz, Serializable id) {
		Object obj = null;
		try {
			startOperation();
			obj = session.get(clazz, id);
			transaction.commit();
		} catch (HibernateException exc) {
			if (transaction != null) transaction.rollback();
			throw exc;
		} finally {
			session.close();
		}
		return obj;
	}

	/**
	 * Find all objects of the specified type in the DB.
	 * @param clazz
	 * @return
	 * @throws HibernateException
	 */
	protected List selectAll(Class clazz) throws HibernateException {
		List objects = null;
		try {
			startOperation();
			objects = session.createCriteria(clazz).list();
			transaction.commit();
		} catch (HibernateException exc) {
			if (transaction != null) transaction.rollback();
			throw exc;
		} finally {
			session.close();
		}
		return objects;
	}

	protected boolean isEmpty(Class clazz) {
		boolean result;
		try {
			startOperation();
			result = session.createCriteria(clazz).uniqueResult() == null;
			transaction.commit();
		} finally {
			session.close();
		}
		return result;
	}

	protected Number count(Class clazz) {
		Number result;
		try {
			startOperation();
			result = (Number) session.createCriteria(clazz)
					.setProjection(Projections.rowCount())
					.uniqueResult();
			transaction.commit();
		} finally {
			session.close();
		}
		return result;
	}

	/**
	 * Find in the DB all objects of the specified type filtered by value of a detached property.
	 * @param clazz
	 * @param property
	 * @param value
	 * @return
	 * @throws HibernateException
	 */
	protected List selectAll(Class clazz, String property, String value) throws HibernateException {
		List objects = null;
		try {
			startOperation();
			Criteria criteria = session.createCriteria(clazz).add(Restrictions.eq(property, value));
			objects = criteria.list();
			transaction.commit();
		} catch (HibernateException exc) {
			if (transaction != null) transaction.rollback();
			throw exc;
		} finally {
			session.close();
		}
		return objects;
	}

	private void startOperation() {
		logger.info("+startOperation()");
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
		logger.info("-startOperation()");
	}
}
