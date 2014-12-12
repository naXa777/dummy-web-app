package by.naxa.dao;

import by.naxa.model.Rate;
import by.naxa.model.Student;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;

import java.util.Collection;

/**
 * Student DAO. Just to fetch initialized list of rates.
 * Created by phomal on 12.12.2014.
 */
public class StudentDAO extends GenericDAO<Student> {

	public StudentDAO() {
		super(Student.class);
	}

	public Collection<Rate> getRates(Student student) {
		try {
			startOperation();

			getCurrentSession().update(student);
			Hibernate.initialize(student.getRates());

			execute();
		} catch (HibernateException exc) {
			rollback();
			throw exc;
		} finally {
			finishOperation();
		}

		return student.getRates();
	}
}
