package by.naxa.services;

import by.naxa.dao.StudentDAO;
import by.naxa.model.Student;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by phomal on 12.12.2014.
 */
@Component
public @Data class StudentService {

	@Autowired
	private StudentDAO studentDAO;

	public void addStudent(Student student) {
		getStudentDAO().insert(student);
	}

	public void deleteStudent(Student student) {
		getStudentDAO().delete(student);
	}

	public Student findStudentById(Long id) {
		return studentDAO.getById(id);
	}

	/**
	 * @return all resources (students) from the database
	 */
	public List<Student> fetchAllStudents() {
		return getStudentDAO().selectAll();
	}
}
