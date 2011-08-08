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
import org.apache.commons.codec.digest.DigestUtils;
//import org.springframework.util.DigestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.RegisterService;

/** 
 *
 * @author vernet
 */
public class RegisterController extends AbstractController {
	
	private RegisterService registerService;
	
	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		//hsr.setCharacterEncoding("UTF-8");
		String name = hsr.getParameter("name");
		String password = hsr.getParameter("password");
		if ((name != null) && (password != null)) {
			User user = new User();
			user.setName(name);
			user.setPassword(DigestUtils.md5Hex(password));
			user.setMessagesOnPage((short) 10);
			user.setAlbumsOnPage((short) 10);
			user.setPhotosOnPage((short) 10);
			user.setRole("ROLE_USER");
			user.setAvatar("img/noAvatar.png");
			registerService.addUser(user);
			ModelAndView mv = new ModelAndView("registeredView");
			mv.addObject("registered_user", name);
			return mv;
		}
		ModelAndView mv = new ModelAndView("registerView");
		mv.addObject("register", new controller.entity.Register());
		//mv.addObject("info_str", hsr.getParameterMap().toString());
		//mv.addObject("session_str", new SessionService().showRequestSession(request));
		UserHelper userHelper = new UserHelper();
		List<User> userList = userHelper.getUserList();
		String userListStr = name + " " + password + "<br />\n";
		for (User user : userList) {
			userListStr += user.getName() + " " + user.getPassword() + " "
							+ user.getRole() + "<br />\n";
		}
		//mv.addObject("user_list_str", userListStr);
		return mv;
	}
}
