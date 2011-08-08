/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import db_objects.Album;
import db_objects.Photo;
import hibernate.AlbumHelper;
import hibernate.PhotoHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import service.PhotoService;

/** 
 *
 * @author vernet
 */
public class PhotoController extends AbstractController {

	private PhotoService photoService;

	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
		String strPhotoId;
		Long photoId = 1L;
		strPhotoId = hsr.getParameter("photoId");
		if (strPhotoId != null) {
			photoId = Long.parseLong(strPhotoId);
		}
		ModelAndView mv = new ModelAndView("photoView");

		String strFormPhotoId = hsr.getParameter("id");
		if (strFormPhotoId != null) {
			Long formPhotoId = Long.parseLong(strFormPhotoId);
			String title = hsr.getParameter("title");
			String description = hsr.getParameter("description");
			if (title == null) {
				title = "";
			}
			if (description == null) {
				description = "";
			}
			PhotoHelper photoHelper = new PhotoHelper();
			Photo photo = photoHelper.getPhotoById(photoId);
			photo.setTitle(title);
			photo.setDescription(description);
			photoHelper.updatePhoto(photo);
			mv.addObject("title_description_changed", "Название и описание изменены<br />");
		}


		PhotoHelper photoHelper = new PhotoHelper();
		Photo photo = photoHelper.getPhotoById(photoId);
		mv.addObject("photo", photo);
		return mv;
	}
}
