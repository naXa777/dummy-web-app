package by.naxa;

import by.naxa.dao.GenericDAO;
import by.naxa.dao.StudentDAO;
import by.naxa.model.Faculty;
import by.naxa.model.Rate;
import by.naxa.model.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Web controller.
 * Manages add and edit resources.
 * Created by phomal on 09.12.2014.
 */
@Controller
@Slf4j
public class AddEditController {

	/**
	 * Create new student or update existent.
	 * @param sid
	 * @param name
	 * @param facultyName
	 * @param ratesString
	 * @return Model and View
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addStudent(@RequestParam(value = "id", required = true) String sid,
	                               @RequestParam(value = "name", required = true) String name,
	                               @RequestParam(value = "faculty", required = true) String facultyName,
	                               @RequestParam(value = "rates", required = false) String ratesString) {

		Long id = StringUtils.isEmpty(sid)? 0L : Long.parseLong(sid);

		// Find a Faculty by its name
		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		Faculty faculty = facultyDAO.find(facultyName);

		// Prepare a Student record
		StudentDAO studentDAO = new StudentDAO();
		Student newStudent = (id <= 0)? new Student() : studentDAO.find(id);

		// Clear student's rate history
		GenericDAO<Rate> rateDAO = new GenericDAO<Rate>(Rate.class);
		Iterable<Rate> existingRates = studentDAO.getRates(newStudent);
		for (Rate rate : existingRates) rateDAO.delete(rate);

		// Parse rates from the string into a List<>
		Iterable<String> ratesList = Arrays.asList(ratesString.split("\\s"));
		List<Rate> rates = new LinkedList<Rate>();
		for (String rateValue : ratesList)
			try {
				Rate rate = new Rate();
				rate.setValue(Integer.parseInt(rateValue));
				rate.setStudent(newStudent);
				rates.add(rate);
			} catch (NumberFormatException exc) {
				log.error("Bad number format: {}", rateValue);
			}

		// Set student's properties
		newStudent.setName(name);
		newStudent.setFaculty(faculty);
		newStudent.setRates(rates);

		// Puff!
		if (id <= 0)
			studentDAO.create(newStudent);
		else
			studentDAO.update(newStudent);

		// Back to the main page
		return new ModelAndView("redirect:/list");
	}

	/**
	 * Just fills Add page with no data.
	 * @return Model and View
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addStudentPage() {
		ModelAndView mav = new ModelAndView("add");

		// Retrieve a list of faculties
		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		List<Faculty> faculties = facultyDAO.findAll();
		mav.addObject("faculties", faculties);

		// Leave other beans empty
		mav.addObject("student", new Student());
		mav.addObject("rates", null);

		return mav;
	}

	/**
	 * Just fills Edit page with the data from student's profile.
	 * @return Model and View
	 */
	@RequestMapping(value = "/add/{id:.+}", method = RequestMethod.GET)
	public ModelAndView editStudentPage(@PathVariable("id") String sid) {
		Long id = StringUtils.isEmpty(sid)? 0L : Long.parseLong(sid);

		ModelAndView mav = new ModelAndView("add");

		// Retrieve a list of faculties
		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		List<Faculty> faculties = facultyDAO.findAll();
		mav.addObject("faculties", faculties);

		// Find the student
		StudentDAO studentDAO = new StudentDAO();
		Student student = studentDAO.find(id);

		if (student == null) {
			log.error("Student with id = {} does not exist.", id);
			student = new Student();
		}

		mav.addObject("student", student);
		mav.addObject("rates", StringUtils.join(studentDAO.getRates(student), " "));

		return mav;
	}

}