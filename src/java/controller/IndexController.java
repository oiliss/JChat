/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.IndexService;
import service.session.SessionService;

/** 
 *
 * @author vernet
 */
public class IndexController extends AbstractController {

	private IndexService indexService;

	public void setIndexService(IndexService indexService) {
		this.indexService = indexService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		SessionService.setSessionUserId(hsr);
		String text = hsr.getParameter("text");
		Long userId = SessionService.getSessionUserId(hsr);
		if (text != null) {
			if (!text.isEmpty()) {
				indexService.addMessage(userId, text);
			}
		}
		String allMessagesReaded = hsr.getParameter("allMessagesReaded");
		if (allMessagesReaded != null) {
			String strLastMessageId = SessionService.get(hsr, "lastMessageId");
			if (strLastMessageId != null) {
				Long lastMessageId = Long.parseLong(strLastMessageId);
				indexService.changeShownDate(userId, lastMessageId);
			}
		}
		SessionService.set(hsr, "lastMessageId", "" + indexService.getLastMessageId());
		ModelAndView mv = new ModelAndView("indexView");
		//mv.addObject("session_content", SessionService.content(hsr));
		mv.addObject("last_readed_message_id",
						indexService.getLastReadedMessageId(userId));
		mv.addObject("new_messages",
						indexService.messageList(SessionService.getSessionUserId(hsr)));
		//mv.addObject("message", new controller.entity.Mess());
		return mv;
	}
}
