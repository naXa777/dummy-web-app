package by.naxa.dao.impl;

import by.naxa.dao.FacultyDAO;
import by.naxa.model.Faculty;
import lombok.Data;
import org.intellij.lang.annotations.Language;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Faculty DAO implementation. Contains methods used in the service business logic.
 * Created by phomal on 12.12.2014.
 */
@Transactional
@Repository("facultyDAO")
public @Data class FacultyDAOJPA2Impl implements FacultyDAO {

	@Language("HQL")    private static final String SELECT_QUERY= "select f from Faculty f";
	@Language("HQL")    private static final String FIND_QUERY  = "select f from Faculty f where name = :name";
	@Language("HQL")    private static final String COUNT_QUERY = "select count(*) from Faculty";

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
	@PersistenceContext private EntityManager entityManager;

	@Override
	public void save(Faculty entity) {
		entityManager.merge(entity);
	}

	@Override
	public Faculty findOne(Serializable pk) {
		return entityManager.find(Faculty.class, pk);
	}

	@Override
	public Faculty findOneByName(String name) {
		Query query = entityManager.createQuery(FIND_QUERY);
		query.setParameter("name", name);
		return (Faculty) query.getSingleResult();
	}

	@Override
	public Iterable<Faculty> findAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		return (Iterable<Faculty>) query.getResultList();
	}

	@Override
	public Long count() {
		Query query = entityManager.createQuery(COUNT_QUERY);
		return (Long) query.getSingleResult();  // As per the JPA specification, COUNT returns a Long.
	}

	@Override
	public void delete(Faculty entity) {
		// We need to check if the entity is managed by EntityManager#contains()
		// and if not, then make it managed by EntityManager#merge().
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));
	}

	@Override
	public boolean exists(Serializable pk) {
		return (entityManager.find(Faculty.class, pk) != null);
	}
}
