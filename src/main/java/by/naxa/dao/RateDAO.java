package by.naxa.dao;

import by.naxa.model.Rate;
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
@Repository("rateDAO")
public @Data class RateDAO {

	private static final String SELECT_QUERY = "select r from Rate r";

	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Rate rate) {
		entityManager.persist(rate);
	}

	public List<Rate> selectAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		return (List<Rate>) query.getResultList();
	}
}
