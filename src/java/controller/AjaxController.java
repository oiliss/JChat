/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.AjaxService;
import service.session.SessionService;

/** 
 *
 * @author vernet
 */
public class AjaxController extends AbstractController {

	private AjaxService ajaxService;

	public void setAjaxService(AjaxService ajaxService) {
		this.ajaxService = ajaxService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		Long lastMessageId = 0L;
		ModelAndView mv = new ModelAndView("ajaxView");
		Long userId = SessionService.getSessionUserId(hsr);
		String strLastMessageId = SessionService.get(hsr, "lastMessageId");
		if (strLastMessageId != null) {
			lastMessageId = Long.parseLong(strLastMessageId);
		}
		SessionService.set(hsr, "lastMessageId", "" + ajaxService.getLastMessageId());
		mv.addObject("new_messages", ajaxService.newMessageList(userId, lastMessageId));
		return mv;
	}
}
