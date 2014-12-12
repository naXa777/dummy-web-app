package by.naxa.springdao;

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

	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Faculty faculty) {
		entityManager.persist(faculty);
	}

	public List<Faculty> selectAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		return (List<Faculty>) query.getResultList();
	}
}
