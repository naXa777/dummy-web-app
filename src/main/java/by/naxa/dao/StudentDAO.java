package by.naxa.dao;

import by.naxa.model.Student;
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
@Repository(value = "studentDAO")
public @Data class StudentDAO {

	private static final String SELECT_QUERY = "select s from Student s";

	@PersistenceContext
	private EntityManager entityManager;

	public void insert(Student student) {
		entityManager.persist(student);
	}

	public void delete(Student student) {
		entityManager.remove(student);
	}

	public List<Student> selectAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		return (List<Student>) query.getResultList();
	}

	public Student getById(Long id) {
		return entityManager.find(Student.class, id);
	}
}
