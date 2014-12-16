package by.naxa.dao.impl;

import by.naxa.dao.StudentDAO;
import by.naxa.model.Student;
import lombok.Data;
import org.intellij.lang.annotations.Language;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.Serializable;

/**
 * Student DAO implementation. Contains methods used in the service business logic.
 * Created by phomal on 12.12.2014.
 */
@Transactional
@Repository(value = "studentDAO")
public @Data class StudentDAOJPA2Impl implements StudentDAO {

	@Language("HQL")    private static final String SELECT_QUERY = "select s from Student s";
	@Language("HQL")    private static final String COUNT_QUERY  = "select count(*) from Student";

	@PersistenceContext private EntityManager entityManager;

	@Override
	public void save(Student entity) {
		entityManager.merge(entity);
	}

	@Override
	public Student findOne(Serializable pk) {
		return entityManager.find(Student.class, pk);
	}

	@Override
	public Iterable<Student> findAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		return (Iterable<Student>) query.getResultList();
	}

	@Override
	public Long count() {
		Query query = entityManager.createQuery(COUNT_QUERY);
		return (Long) query.getSingleResult();  // As per the JPA specification, COUNT returns a Long.
	}

	@Override
	public void delete(Student entity) {
		// We need to check if the entity is managed by EntityManager#contains()
		// and if not, then make it managed by EntityManager#merge().
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));
	}

	@Override
	public boolean exists(Serializable pk) {
		return (entityManager.find(Student.class, pk) != null);
	}

}
