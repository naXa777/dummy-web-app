package by.naxa.services;

import by.naxa.dao.FacultyDAO;
import by.naxa.model.Faculty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by phomal on 12.12.2014.
 */
@Component
public @Data class FacultyService {

	@Autowired
	private FacultyDAO facultyDAO;

	public void addFaculty(Faculty faculty) {
		getFacultyDAO().save(faculty);
	}

	public Faculty findFaculty(String name) {
		return getFacultyDAO().findOneByName(name);
	}

	public boolean isEmpty() {
		return (getFacultyDAO().count() <= 0);
	}

	public Iterable<Faculty> fetchAllFaculties() {
		return getFacultyDAO().findAll();
	}
}
