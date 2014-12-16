package by.naxa.dao.impl;

import by.naxa.dao.RateDAO;
import by.naxa.model.Rate;
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
 * Rate DAO implementation. Contains methods used in the service business logic.
 * Created by phomal on 12.12.2014.
 */
@Transactional
@Repository("rateDAO")
public @Data class RateDAOJPA2Impl implements RateDAO {

	@Language("HQL")    private static final String FIND_QUERY  = "select r from Rate r where r.student.id = :stud_id";
	@Language("HQL")    private static final String COUNT_QUERY = "select count(*) from Rate";
	@Language("HQL")    private static final String DELETE_QUERY= "delete from Rate where student.id = :stud_id";

	@PersistenceContext private EntityManager entityManager;

	@Override
	public void save(Rate entity) {
		entityManager.persist(entity);
	}

	@Override
	public Rate findOne(Serializable pk) {
		return entityManager.find(Rate.class, pk);
	}

	@Override
	public Iterable<Rate> findRatesOf(Student student) {
		Query query = entityManager.createQuery(FIND_QUERY);
		query.setParameter("stud_id", student.getId());
		return (Iterable<Rate>) query.getResultList();
	}

	@Override
	public Long count() {
		Query query = entityManager.createQuery(COUNT_QUERY);
		return (Long) query.getSingleResult();  // As per the JPA specification, COUNT returns a Long.
	}

	@Override
	public void delete(Rate entity) {
		// We need to check if the entity is managed by EntityManager#contains()
		// and if not, then make it managed by EntityManager#merge().
		entityManager.remove(entityManager.contains(entity)? entity : entityManager.merge(entity));
	}

	@Override
	public boolean exists(Serializable pk) {
		return (entityManager.find(Rate.class, pk) != null);
	}

	@Override
	public void deleteRatesOf(Student student) {
		Query query = entityManager.createQuery(DELETE_QUERY);
		query.setParameter("stud_id", student.getId());
		query.executeUpdate();
	}
}
