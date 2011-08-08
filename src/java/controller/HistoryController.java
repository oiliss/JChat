/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.HistoryService;
import service.session.SessionService;

/** 
 *
 * @author vernet
 */
public class HistoryController extends AbstractController {

	private HistoryService historyService;

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		int pageNum = 1;
		String strPageNum;
		strPageNum = SessionService.get(hsr, "pageNum");
		if (strPageNum != null) {
			pageNum = (int) Integer.parseInt(strPageNum);
		}
		strPageNum = hsr.getParameter("page_num");
		if (strPageNum != null) {
			pageNum = (int) Integer.parseInt(strPageNum);
		}
		SessionService.set(hsr, "pageNum", "" + pageNum);

		ModelAndView mv = new ModelAndView("historyView");
		Long userId = SessionService.getSessionUserId(hsr);
		mv.addObject("new_messages", historyService.messageList(userId, pageNum));
		mv.addObject("page_links", historyService.linkList(userId, pageNum));
		return mv;
	}
}
