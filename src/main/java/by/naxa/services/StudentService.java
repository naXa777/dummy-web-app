package by.naxa.services;

import by.naxa.model.Student;
import by.naxa.springdao.StudentDAO;
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

	public void addFaculty(Student student) {
		getStudentDAO().insert(student);
	}

	public List<Student> fetchAllFaculties() {
		return getStudentDAO().selectAll();
	}
}
