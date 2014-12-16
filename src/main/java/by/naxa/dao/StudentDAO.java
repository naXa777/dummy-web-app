package by.naxa.dao;

import by.naxa.model.Student;

import java.io.Serializable;

/**
 * TODO: Use Spring JPA Repositories for this abstraction.
 * Student data access object interface.
 * Created by phomal on 16.12.2014.
 */
public interface StudentDAO {

	void save(Student entity);

	Student findOne(Serializable pk);

	Iterable<Student> findAll();

	Long count();

	void delete(Student entity);

	boolean exists(Serializable pk);
}
