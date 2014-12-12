package by.naxa.dao;

import by.naxa.dao.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.util.List;

/**
 * Abstract DAO. Consists of typical transactions.
 * @see org.hibernate.Session
 * Created by phomal on 09.12.2014.
 */
@Slf4j
public abstract class AbstractDAO {

	private Session session;
	private Transaction transaction;

	protected AbstractDAO() {}

	protected void doSaveOrUpdate(Object obj) {
		try {
			startOperation();
			session.saveOrUpdate(obj);
			execute();
		} catch (HibernateException exc) {
			rollback();
			throw exc;
		} finally {
			finishOperation();
		}
	}

	protected void doMerge(Object obj) {
		try {
			startOperation();
			session.saveOrUpdate(obj);
			execute();
		} catch (HibernateException exc) {
			rollback();
			throw exc;
		} finally {
			finishOperation();
		}
	}

	protected void doDelete(Object obj) {
		try {
			startOperation();
			session.delete(obj);
			execute();
		} catch (HibernateException exc) {
			rollback();
			throw exc;
		} finally {
			finishOperation();
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
			execute();
		} catch (HibernateException exc) {
			rollback();
			throw exc;
		} finally {
			finishOperation();
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
			//objects = session.createCriteria(clazz).list();
			objects = session.createQuery("From " + clazz.getName()).list();
			execute();
		} catch (HibernateException exc) {
			rollback();
			throw exc;
		} finally {
			finishOperation();
		}
		return objects;
	}

	protected void execute() {
		transaction.commit();
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
			objects = session.createCriteria(clazz).add(Restrictions.eq(property, value)).list();
			execute();
		} catch (HibernateException exc) {
			rollback();
			throw exc;
		} finally {
			finishOperation();
		}
		return objects;
	}

	protected boolean isEmpty(Class clazz) {
		boolean result;
		try {
			startOperation();
			result = session.createCriteria(clazz).uniqueResult() == null;
			execute();
		} finally {
			finishOperation();
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
			execute();
		} finally {
			finishOperation();
		}
		return result;
	}

	protected void startOperation() {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}

	protected void rollback() {
		if (transaction != null) transaction.rollback();
	}

	protected void finishOperation() {
		session.close();
	}

	protected Session getCurrentSession() {
		return session;
	}
}
