package by.naxa.dao;

import by.naxa.model.Faculty;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by phomal on 12.12.2014.
 */
@Transactional
@Repository("facultyDAO")
public @Data class FacultyDAO {

	private static final String SELECT_QUERY = "select f from Faculty f";

	/*
	 * The EntityManager API is used to access a database in a particular unit of work.
	 * It is used to create and remove persistent entity instances,
	 * to find entities by their primary key identity, and to query over all entities.
	 * This interface is similar to the Session in Hibernate.
	 *
	 * A persistence context is a set of entity instances in which for any persistent
	 * entity identity there is a unique entity instance. Within the persistence context,
	 * the entity instances and their lifecycle is managed by a particular entity manager.
	 * The scope of this context can either be the transaction, or an extended unit of work.
	 */
	@PersistenceContext(unitName = "universityPersistenceUnit")
	private EntityManager entityManager;

	public void insert(Faculty faculty) {
		entityManager.persist(faculty);
	}

	public List<Faculty> selectAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		return (List<Faculty>) query.getResultList();
	}
}
