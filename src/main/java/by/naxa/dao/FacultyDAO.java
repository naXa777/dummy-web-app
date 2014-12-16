package by.naxa.dao;

import by.naxa.model.Faculty;

import java.io.Serializable;

/**
 * TODO: Use Spring JPA Repositories for this abstraction.
 * Faculty data access object interface.
 * Created by phomal on 16.12.2014.
 */
public interface FacultyDAO {

	void save(Faculty entity);

	Faculty findOne(Serializable pk);

	Faculty findOneByName(String name);

	Iterable<Faculty> findAll();

	Long count();

	void delete(Faculty entity);

	boolean exists(Serializable pk);
}
