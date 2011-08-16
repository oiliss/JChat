/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.ClientService;
import service.IndexService;
import service.session.SessionService;

/** 
 *
 * @author vernet
 */
public class ClientController extends AbstractController {

	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		ModelAndView mv = new ModelAndView("clientView");

		String userNsme = hsr.getParameter("userName");
		String userPassword = hsr.getParameter("userPassword");
		if ((userNsme != null) && (userPassword != null)) {
			if (!userNsme.isEmpty() && !userPassword.isEmpty()) {
				mv.addObject("connected",
								clientService.login(hsr, userNsme, userPassword));
				return mv;
			}
		}

		String strUserId = SessionService.get(hsr, "clientUserId");
		if (strUserId == null) {
			mv.addObject("connected", "Reject");
			return mv;
		}
		Long userId = Long.parseLong(strUserId);
		if (userId == null) {
			mv.addObject("connected", "Reject");
			return mv;
		}

		String text = hsr.getParameter("text");
		if (text != null) {
			if (!text.isEmpty()) {
				IndexService indexService = new IndexService();
				indexService.addMessage(userId, text);
			}
			mv.addObject("connected", "Accept");
			return mv;
		}

		String strLastMessageId = hsr.getParameter("lastMessageId");
		if (strLastMessageId != null) {
			if (!strLastMessageId.isEmpty()) {
				Long lastMessageId = 0L;
				lastMessageId = Long.parseLong(strLastMessageId);
				mv.addObject("new_messages", clientService.newMessageList(userId, lastMessageId));
				return mv;
			}
		}

		return mv;

	}
}
