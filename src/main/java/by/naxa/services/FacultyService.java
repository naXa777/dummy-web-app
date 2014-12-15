package by.naxa.services;

import by.naxa.dao.FacultyDAO;
import by.naxa.model.Faculty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by phomal on 12.12.2014.
 */
@Component
public @Data class FacultyService {

	@Autowired
	private FacultyDAO facultyDAO;

	public void addFaculty(Faculty faculty) {
		getFacultyDAO().insert(faculty);
	}

	public List<Faculty> fetchAllFaculties() {
		return getFacultyDAO().selectAll();
	}
}
