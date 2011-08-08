/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db_objects.Album;
import hibernate.AlbumHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.AlbumPhotosService;
import service.session.SessionService;

/** 
 *
 * @author vernet
 */
public class AlbumPhotosController extends AbstractController {

	private AlbumPhotosService albumPhotosService;

	public void setAlbumPhotosService(AlbumPhotosService albumPhotosService) {
		this.albumPhotosService = albumPhotosService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		String strAlbumId;
		Long albumId = null;
		strAlbumId = SessionService.get(hsr, "albumId");
		if (strAlbumId != null) {
			albumId = Long.parseLong(strAlbumId);
		}
		strAlbumId = hsr.getParameter("albumId");
		if (strAlbumId != null) {
			albumId = Long.parseLong(strAlbumId);
		}
		if (albumId != null) {
			SessionService.set(hsr, "albumId", "" + albumId);
		}
		int pageNum = 1;
		String strPageNum;
		strPageNum = SessionService.get(hsr, "albumPhotosPageNum");
		if (strPageNum != null) {
			pageNum = (int) Integer.parseInt(strPageNum);
		}
		strPageNum = hsr.getParameter("page_num");
		if (strPageNum != null) {
			pageNum = (int) Integer.parseInt(strPageNum);
		}
		SessionService.set(hsr, "albumPhotosPageNum", "" + pageNum);

		ModelAndView mv = new ModelAndView("albumPhotosView");

		String strFormAlbumId = hsr.getParameter("id");
		if (strFormAlbumId != null) {
			Long formAlbumId = Long.parseLong(strFormAlbumId);
			String title = hsr.getParameter("title");
			String description = hsr.getParameter("description");
			if (title == null) {
				title = "";
			}
			if (description == null) {
				description = "";
			}
			AlbumHelper albumHelper = new AlbumHelper();
			Album album = albumHelper.getAlbumById(formAlbumId);
			album.setTitle(title);
			album.setDescription(description);
			albumHelper.updateAlbum(album);
			mv.addObject("title_description_changed", "Название и описание изменены<br />");
		}

		if (albumPhotosService.addPhoto(hsr)) {
			mv.addObject("photo_added", "Фотография добавлена<br />");
		}

		/*mv.addObject("session_content", SessionService.content(hsr)
						+ SessionService.request(hsr));*/
		mv.addObject("photos", albumPhotosService.photoList(albumId, pageNum));
		mv.addObject("page_links", albumPhotosService.linkList(albumId, pageNum));
		AlbumHelper albumHelper = new AlbumHelper();
		Album album = albumHelper.getAlbumById(albumId);
		mv.addObject("album", album);
		return mv;

	}
}
