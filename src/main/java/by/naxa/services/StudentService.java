package by.naxa.services;

import by.naxa.dao.RateDAO;
import by.naxa.dao.StudentDAO;
import by.naxa.model.Rate;
import by.naxa.model.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by phomal on 12.12.2014.
 */
@Component
public @Data class StudentService {

	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private RateDAO rateDAO;

	public void addStudent(Student newStudent) {
		Student oldStudent = getStudentDAO().findOne(newStudent.getId());
		// Clear student's rate history.
		if (oldStudent != null)
			getRateDAO().deleteRatesOf(oldStudent);
		// Save student. His rates will be saved automatically.
		getStudentDAO().save(newStudent);
	}

	public Iterable<Rate> getRates(Student student) {
		return getRateDAO().findRatesOf(student);
	}

	public void deleteStudent(Student student) {
		getStudentDAO().delete(student);
	}

	public Student findStudentById(Long id) {
		return getStudentDAO().findOne(id);
	}

	/**
	 * @return all resources (students) from the database
	 */
	public Iterable<Student> fetchAllStudents() {
		return getStudentDAO().findAll();
	}
}
