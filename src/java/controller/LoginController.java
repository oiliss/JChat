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
import service.LoginService;

/** 
 *
 * @author vernet
 */
public class LoginController extends AbstractController {

	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		ModelAndView mv = new ModelAndView("loginView");
		mv.addObject("login", new controller.entity.Register());
		//mv.addObject("info_str", hsr.getParameterMap().toString());
		//mv.addObject("session_str", new SessionService().showRequestSession(request));
		UserHelper userHelper = new UserHelper();
		List<User> userList = userHelper.getUserList();
		String userListStr = "";
		for (User user : userList) {
			userListStr += user.getName() + " " + user.getPassword() + " "
							+ user.getRole() + "<br />\n";
		}
		//mv.addObject("user_list_str", userListStr);
		String loginError = hsr.getParameter("login_error");
		if ((loginError != null) && (loginError.equals("1"))) {
		mv.addObject("login_error", "Ошибка! Неправильно заданы имя и пароль.");
		}

		return mv;
	}
}
