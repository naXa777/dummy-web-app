package by.naxa.dao;

import java.util.List;

/**
 * Generalized DAO.
 * Created by phomal on 09.12.2014.
 */
public class GenericDAO<T> extends AbstractDAO {

	private final Class<T> clazz;

	public GenericDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	/**
	 * Insert a new object into the DB.
	 * @param model
	 */
	public void create(T model) {
		super.doSaveOrUpdate(model);
	}

	/**
	 * Update a state of the detached object.
	 * @param model
	 */
	public void update(T model) {
		super.doSaveOrUpdate(model);
	}

	/**
	 * Delete the specified object.
	 * @param model
	 */
	public void delete(T model) {
		super.doDelete(model);
	}

	/**
	 * Find an object by its primary key.
	 * @param id primary key
	 * @return object or null
	 */
	public T find(Long id) {
		return (T) super.doFind(clazz, id);
	}

	/**
	 * Find all objects of the specified type in the DB.
	 * @return
	 */
	public List<T> findAll() {
		return (List<T>) super.selectAll(clazz);
	}

}
