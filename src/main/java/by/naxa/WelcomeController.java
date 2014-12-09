package by.naxa;

import by.naxa.dao.GenericDAO;
import by.naxa.model.Faculty;
import by.naxa.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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


	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView init() {

		ModelAndView mav = new ModelAndView("index");

		String LeedsFaculties[] = {
				"Faculty of Arts",
				"Faculty of Biological Science",
				"Faculty of Business",
				"Faculty of Engineering (includes the School of Computing)",
				"Faculty of Environment",
				"Faculty of Mathematics and Physical Sciences",
				"Faculty of Medicine and Health",
				"Faculty of Performance, Visual Arts and Communication" };

		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		for (String name : LeedsFaculties)
			facultyDAO.create(new Faculty(name));
		mav.addObject("name", "initialization OK.");

		return mav;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView students() {

		ModelAndView mav = new ModelAndView("list");

		//GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		//List<Faculty> faculties = facultyDAO.findAll();

		GenericDAO<Student> studentDAO = new GenericDAO<Student>(Student.class);
		List<Student> allStudents = studentDAO.findAll();
		mav.addObject("students", allStudents);

		return mav;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addStudent() throws IllegalAccessException {

		ModelAndView mav = new ModelAndView("add");

		throw new IllegalAccessException("/add Not yet implemented.");
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView editStudent() {

		ModelAndView mav = new ModelAndView("add");

		GenericDAO<Faculty> facultyDAO = new GenericDAO<Faculty>(Faculty.class);
		List<Faculty> faculties = facultyDAO.findAll();
		mav.addObject("faculties", faculties);

		GenericDAO<Student> studentDAO = new GenericDAO<Student>(Student.class);
		Student student = studentDAO.find( 1L );
		mav.addObject("student", student);

		return mav;
	}

}
