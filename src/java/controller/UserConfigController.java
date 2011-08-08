/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db_objects.User;
import hibernate.UserHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.UserConfigService;
import service.session.SessionService;

/**
 *
 * @author vernet
 */
public class UserConfigController extends AbstractController {

	private UserConfigService userConfigService;

	public void setUserConfigService(UserConfigService userConfigService) {
		this.userConfigService = userConfigService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		ModelAndView mv = new ModelAndView("userConfigView");
		if (userConfigService.update(hsr)) {
			mv.addObject("changes_saved", "Изменения сохранены<br />");
		}
		//mv.addObject("session_content", SessionService.content(hsr));
		//mv.addObject("request_content", SessionService.request(hsr));
		UserHelper userHelper = new UserHelper();
		User user = userHelper.getUserById(SessionService.getSessionUserId(hsr));
		mv.addObject("user", user);
		String avatar = user.getAvatar();
		if (avatar != null) {
			mv.addObject("avatar", avatar);
		}
		return mv;
	}
}
