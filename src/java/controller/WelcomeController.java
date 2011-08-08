/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db_objects.User;
import hibernate.UserHelper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.WelcomeService;

/** 
 *
 * @author vernet
 */
public class WelcomeController extends AbstractController {

	private WelcomeService welcomeService;

	public void setWelcomeService(WelcomeService welcomeService) {
		this.welcomeService = welcomeService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		ModelAndView mv = new ModelAndView("welcomeView");
		UserHelper userHelper = new UserHelper();
		List<User> userList = userHelper.getUserList();
		String userListStr = "";
		for (User user : userList) {
			userListStr += user.getName() + " " + user.getPassword() + " "
							+ user.getRole() + "<br />\n";
		}
		mv.addObject("version_str", welcomeService.version());
		return mv;
	}
}
