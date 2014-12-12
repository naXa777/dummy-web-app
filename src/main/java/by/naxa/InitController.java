package by.naxa;

import by.naxa.model.Faculty;
import by.naxa.springdao.FacultyDAO;
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
	 * Quickly fill the  faculties table.
	 */
	@RequestMapping(value = "/init", method = RequestMethod.GET)
	public ModelAndView init() {
		FacultyDAO facultyDAO = new FacultyDAO();

		//if (facultyDAO.isEmpty()) {
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
				facultyDAO.insert(new Faculty(name));
		//}

		return new ModelAndView("redirect:/list");
	}

}
