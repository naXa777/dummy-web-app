package by.naxa.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by phomal on 09.12.2014.
 */
public abstract class AbstractDAO {

	private Session session;
	private Transaction transaction;

	protected AbstractDAO() {}

	protected void doSaveOrUpdate(Object obj) {
		try {
			startOperation();
			session.saveOrUpdate(obj);
			transaction.commit();
		} finally {
			HibernateUtil.shutdown();
		}
	}

	protected void doDelete(Object obj) {
		try {
			startOperation();
			session.delete(obj);
			transaction.commit();
		} finally {
			HibernateUtil.shutdown();
		}
	}

	protected Object doFind(Class clazz, Long id) {
		Object obj = null;
		try {
			startOperation();
			obj = session.load(clazz, id);
			transaction.commit();
		} finally {
			HibernateUtil.shutdown();
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
			Query query = session.createQuery("from " + clazz.getName());
			objects = query.list();
			transaction.commit();
		} finally {
			HibernateUtil.shutdown();
		}
		return objects;
	}

	private void startOperation() {
		session = HibernateUtil.getSessionFactory().openSession();
		transaction = session.beginTransaction();
	}
}
