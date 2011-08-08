/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.AlbumsService;
import service.session.SessionService;

/** 
 *
 * @author vernet
 */
public class AlbumsController extends AbstractController {

	private AlbumsService albumsService;

	public void setAlbumsService(AlbumsService albumsService) {
		this.albumsService = albumsService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		int pageNum = 1;
		String strPageNum;
		strPageNum = SessionService.get(hsr, "albumPageNum");
		if (strPageNum != null) {
			pageNum = (int) Integer.parseInt(strPageNum);
		}
		strPageNum = hsr.getParameter("page_num");
		if (strPageNum != null) {
			pageNum = (int) Integer.parseInt(strPageNum);
		}
		SessionService.set(hsr, "albumPageNum", "" + pageNum);
		String title = hsr.getParameter("title");
		String description = hsr.getParameter("description");
		if ((title != null) || (description != null)) {
			if (title == null) {
				title = "";
			}
			if (description == null) {
				description = "";
			}
			Long userId = SessionService.getSessionUserId(hsr);
			albumsService.addAlbum(userId, title, description);
		}

		ModelAndView mv = new ModelAndView("albumsView");
		Long userId = SessionService.getSessionUserId(hsr);
		mv.addObject("albums", albumsService.albumList(userId, pageNum));
		mv.addObject("page_links", albumsService.linkList(userId, pageNum));
		mv.addObject("new_album", new db_objects.Album());
		return mv;
	}
}
