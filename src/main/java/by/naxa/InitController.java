package by.naxa;

import by.naxa.dao.GenericDAO;
import by.naxa.model.Faculty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Quick initialization.
 * Created by phomal on 11.12.2014.
 */
@Controller
public class InitController {

	/**
	 * Quickly fill the faculties table.
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView init() {
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

		return new ModelAndView("redirect:/add");
	}

}
