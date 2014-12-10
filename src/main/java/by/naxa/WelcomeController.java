package by.naxa;

import by.naxa.dao.GenericDAO;
import by.naxa.model.Faculty;
import by.naxa.model.Rate;
import by.naxa.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * Created by phomal on 09.12.2014.
 */
@Controller
public class WelcomeController {

	private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

	@RequestMapping(value = "/hello/{name:.+}", method = RequestMethod.GET)
	public ModelAndView welcome(@PathVariable("name") String name) {

		ModelAndView mav = new ModelAndView("index");
		mav.addObject("name", name);

		return mav;
	}

	/**
	 * Quickly fill faculties table.
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView init() {
		logger.info("+init()");

		ModelAndView mav = new ModelAndView("index");

		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);

		if (facultyDAO.isEmpty()) {
			String LeedsFaculties[] = {
					"Faculty of Arts",
					"Faculty of Biological Science",
					"Faculty of Business",
					"Faculty of Engineering (includes the School of Computing)",
					"Faculty of Environment",
					"Faculty of Mathematics and Physical Sciences",
					"Faculty of Medicine and Health",
					"Faculty of Performance, Visual Arts and Communication"};

			for (String name : LeedsFaculties)
				facultyDAO.create(new Faculty(name));
		}
		mav.addObject("name", "initialization OK.");

		return mav;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView students() {
		logger.info("+students()");

		ModelAndView mav = new ModelAndView("list");

		GenericDAO<Student> studentDAO = new GenericDAO<Student>(Student.class);
		List<Student> allStudents = studentDAO.findAll();
		mav.addObject("students", allStudents);

		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addStudent(@RequestParam(value = "id", required = true) Long id,
	                               @RequestParam(value = "name", required = true) String name,
	                               @RequestParam(value = "faculty", required = true) String facultyName,
	                               @RequestParam(value = "rates", required = false) String ratesString)
			throws IllegalAccessException {
		logger.info("+addStudent()");

		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		Faculty faculty = facultyDAO.findAll(facultyName).get(0);

		// Parse ratesString into the List<Rate>
		List<String> ratesList = Arrays.asList(ratesString.split("\\\\s*"));
		List<Rate> rates = new LinkedList<Rate>();
		for (String rateValue : ratesList) {
			try {
				Rate rate = new Rate();
				rate.setValue(Integer.parseInt(rateValue));
				rates.add(rate);
			} catch (NumberFormatException exc) {
				logger.error("Bad number format: %s", rateValue);
			}
		}

		GenericDAO<Student> studentDAO = new GenericDAO<Student>(Student.class);
		Student newStudent;
		newStudent = (id <= 0)? new Student() : studentDAO.find(id);

		newStudent.setName(name);
		newStudent.setFaculty(faculty);
		newStudent.setRates(rates);

		logger.info("addStudent: Puff!");
		// Puff!
		studentDAO.create(newStudent);

		// Back to the main page
		ModelAndView mav = new ModelAndView("redirect:/list");
		logger.info("-addStudent()");
		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView editStudent() {
		logger.info("+editStudent()");

		ModelAndView mav = new ModelAndView("add");

		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		List<Faculty> faculties = facultyDAO.findAll();
		mav.addObject("faculties", faculties);

		GenericDAO<Student> studentDAO = new GenericDAO<Student>(Student.class);
		Student student = studentDAO.find( 1L );

		logger.info(student == null? "Student record is empty." : "Student " + student.getName() + " selected.");

		mav.addObject("student", student);
		mav.addObject("facultyName", (student != null)? student.getFaculty().getName() : null);

		return mav;
	}

}