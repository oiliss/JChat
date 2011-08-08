/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.entity.Link;
import db_objects.Album;
import db_objects.Photo;
import db_objects.User;
import hibernate.AlbumHelper;
import hibernate.PhotoHelper;
import hibernate.UserHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javaoinlibrary.FileIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import service.session.SessionService;

/**
 *
 * @author vernet
 */
public class AlbumPhotosService {

	public String Welcome() {
		String s;
		s = "Welcome!";
		return s;
	}
	/*
	public void addPhoto(Long albumId, String fileName) {
	Photo photo = new Photo();
	Album album = new Album();
	album.setId(albumId);
	photo.setAlbum(album);
	photo.setFile(fileName);
	photo.setTitle("");
	photo.setDescription("");
	PhotoHelper photoHelper = new PhotoHelper();
	photoHelper.addPhoto(photo);
	return;
	}
	 */

	public boolean addPhoto(HttpServletRequest hsr) {
		boolean added = false;
		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(hsr);
			for (FileItem item : items) {
				if (item.isFormField()) {
				} else {
					String filename = FilenameUtils.getName(item.getName());
					if (!filename.equals("")) {
						String programmDir = FileIO.programmDir(getClass());
						PhotoHelper photoHelper = new PhotoHelper();
						String photoDBFileName = "/images/photo_"
										+ (photoHelper.getMaxPhotoId() + 1) + ""
										+ filename.substring(filename.lastIndexOf("."));
						String photoFileName = "";
						if (programmDir.indexOf("O:") == 0) {
							photoFileName += "C:/Temp";
						} else {
							photoFileName += "";
						}
						photoFileName += photoDBFileName;
						try {
							item.write(new File(photoFileName));
							Photo photo = new Photo();
							Album album = new Album();
							album.setId(Long.parseLong(SessionService.get(hsr, "albumId")));
							photo.setAlbum(album);
							photo.setFile(photoDBFileName);
							photo.setTitle("");
							photo.setDescription("");
							photoHelper.addPhoto(photo);
							added = true;
						} catch (Exception ex) {
							System.err.println("Ошибка записи файла " + photoFileName);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Не удается загрузить файл");
		}

		return added;
	}

	public List<Photo> photoList(Long albumId, int pageNum) {
		AlbumHelper albumHelper = new AlbumHelper();
		Album album = albumHelper.getAlbumById(albumId);
		Long userId = album.getUser().getId();
		UserHelper userHelper = new UserHelper();
		User user = userHelper.getUserById(userId);
		PhotoHelper photoHelper = new PhotoHelper();
		int photoCount = photoHelper.getPhotoCount(user.getId());
		short photosOnPage = user.getPhotosOnPage();
		if (photosOnPage < 1) {
			photosOnPage = 1;
		}
		List<Photo> photos =
						photoHelper.getPhotoPage(albumId,
						(pageNum - 1) * photosOnPage, photosOnPage);
		return photos;
	}

	public List<Link> linkList(Long albumId, int pageNum) {
		AlbumHelper albumHelper = new AlbumHelper();
		Album album = albumHelper.getAlbumById(albumId);
		Long userId = album.getUser().getId();
		UserHelper userHelper = new UserHelper();
		User user = userHelper.getUserById(userId);
		PhotoHelper photoHelper = new PhotoHelper();
		int photoCount = photoHelper.getPhotoCount(albumId);
		short photosOnPage = user.getPhotosOnPage();
		if (photosOnPage < 1) {
			photosOnPage = 1;
		}
		List<Link> linkList = new ArrayList();
		Link link;
		int linkPageCount = (int) Math.ceil((double) photoCount / photosOnPage);
		int linkPageNum = 0;
		String url = "/albumphotos.htm?page_num=";

		link = new Link();
		link.setTitle("[ Первая ]");
		if (pageNum > 1) {
			link.setLink(url + 1);
		} else {
			link.setLink("");
		}
		linkList.add(link);

		link = new Link();
		link.setTitle("[ Предыдущая ]");
		if (pageNum > 1) {
			link.setLink(url + (pageNum - 1));
		} else {
			link.setLink("");
		}
		linkList.add(link);

		for (int i = 1; i <= linkPageCount; i++) {
			link = new Link();
			link.setTitle("[ " + i + " ]");
			if (i != pageNum) {
				link.setLink(url + i);
			} else {
				link.setLink("");
			}
			linkList.add(link);
		}

		link = new Link();
		link.setTitle("[ Следующая ]");
		if (pageNum < linkPageCount) {
			link.setLink(url + (pageNum + 1));
		} else {
			link.setLink("");
		}
		linkList.add(link);

		link = new Link();
		link.setTitle("[ Последняя ]");
		if (pageNum < linkPageCount) {
			link.setLink(url + linkPageCount);
		} else {
			link.setLink("");
		}
		linkList.add(link);


		return linkList;
	}
}
