/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.entity.Link;
import db_objects.Album;
import db_objects.User;
import hibernate.AlbumHelper;
import hibernate.UserHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vernet
 */
public class AlbumsService {

	public String Welcome() {
		String s;
		s = "Welcome!";
		return s;
	}

	public void addAlbum(Long userId, String title, String description) {
		Album album = new Album();
		User user = new User();
		user.setId(userId);
		album.setUser(user);
		album.setTitle(title);
		album.setDescription(description);
		AlbumHelper albumHelper = new AlbumHelper();
		albumHelper.addAlbum(album);
		return;
	}

	public List<Album> albumList(Long userId, int pageNum) {
		UserHelper userHelper = new UserHelper();
		AlbumHelper albumHelper = new AlbumHelper();
		User user = userHelper.getUserById(userId);
		int albumCount = albumHelper.getAlbumCount(userId);
		short albumsOnPage = user.getAlbumsOnPage();
		if (albumsOnPage < 1) {
			albumsOnPage = 1;
		}
		List<Album> albums =
						albumHelper.getAlbumPage(userId,
						(pageNum - 1) * albumsOnPage, albumsOnPage);
		return albums;
	}

	public List<Link> linkList(Long userId, int pageNum) {
		UserHelper userHelper = new UserHelper();
		AlbumHelper albumHelper = new AlbumHelper();
		User user = userHelper.getUserById(userId);
		int albumCount = albumHelper.getAlbumCount();
		short albumsOnPage = user.getAlbumsOnPage();
		if (albumsOnPage < 1) {
			albumsOnPage = 1;
		}
		List<Link> linkList = new ArrayList();
		Link link;
		int linkPageCount = (int) Math.ceil((double) albumCount / albumsOnPage);
		int linkPageNum = 0;
		String url = "/albums.htm?page_num=";

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
