/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db_objects.User;
import hibernate.UserHelper;
import java.io.File;
import java.util.List;
import javaoinlibrary.FileIO;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import service.session.SessionService;

/**
 *
 * @author vernet
 */
public class UserConfigService {

	public String Welcome() {
		String s;
		s = "Welcome!";
		return s;
	}

	public boolean update(HttpServletRequest hsr) {
		UserHelper userHelper = new UserHelper();
		User user = userHelper.getUserById(SessionService.getSessionUserId(hsr));
		String name = hsr.getParameter("name");
		String password = hsr.getParameter("password");
		String messagesOnPage = hsr.getParameter("messagesOnPage");
		String albumsOnPage = hsr.getParameter("albumsOnPage");
		String photosOnPage = hsr.getParameter("photosOnPage");
		String avatar = hsr.getParameter("avatar");
		boolean needToSave = false;
		if (name != null) {
			if (!name.equals(user.getName())) {
				user.setName(name);
				System.out.println("name = " + name);
				needToSave = true;
			}
		}
		if (password != null) {
			if (!password.equals(user.getPassword())) {
				user.setPassword(DigestUtils.md5Hex(password));
				System.out.println("password = " + password);
				needToSave = true;
			}
		}
		if (messagesOnPage != null) {
			short nMessagesOnPage = (short) Integer.parseInt(messagesOnPage);
			if (nMessagesOnPage != user.getMessagesOnPage()) {
				if (nMessagesOnPage < 1) {
					nMessagesOnPage = 1;
				}
				user.setMessagesOnPage(nMessagesOnPage);
				System.out.println("nMessagesOnPage = " + nMessagesOnPage);
				needToSave = true;
			}
		}
		if (albumsOnPage != null) {
			short nAlbumsOnPage = (short) Integer.parseInt(albumsOnPage);
			if (nAlbumsOnPage != user.getAlbumsOnPage()) {
				if (nAlbumsOnPage < 1) {
					nAlbumsOnPage = 1;
				}
				user.setAlbumsOnPage(nAlbumsOnPage);
				System.out.println("nAlbumsOnPage = " + nAlbumsOnPage);
				needToSave = true;
			}
		}
		if (photosOnPage != null) {
			short nPhotosOnPage = (short) Integer.parseInt(photosOnPage);
			if (nPhotosOnPage != user.getPhotosOnPage()) {
				if (nPhotosOnPage < 1) {
					nPhotosOnPage = 1;
				}
				user.setPhotosOnPage(nPhotosOnPage);
				System.out.println("nPhotosOnPage = " + nPhotosOnPage);
				needToSave = true;
			}
		}

		try {
			List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(hsr);
			for (FileItem item : items) {
				if (item.isFormField()) {
				} else {
					String filename = FilenameUtils.getName(item.getName());
					if (!filename.equals("")) {
						String programmDir = FileIO.programmDir(getClass());
						String avatarDBFileName = "/images/user_" + user.getId()
										+ "_avatar"
										+ filename.substring(filename.lastIndexOf("."));
						String avatarFileName = "";
						if (programmDir.indexOf("O:") == 0) {
							avatarFileName += "C:/Temp";
						} else {
							//avatarFileName += "/var/lib/tomcat6/webapps/JChat";
							//avatarFileName += "/usr/share/tomcat6-root/default_root";
							avatarFileName += "";
						}
						avatarFileName += avatarDBFileName;
						try {
							item.write(new File(avatarFileName));
							user.setAvatar(avatarDBFileName);
							System.out.println("filename = " + filename);
							needToSave = true;
						} catch (Exception ex) {
							System.err.println("Ошибка записи файла " + avatarFileName);
						}
					}
				}
			}
		} catch (Exception e) {
			System.err.println("Не удается загрузить файл");
		}

		if (needToSave) {
			System.out.print("Updating user ...");
			userHelper.updateUser(user);
			System.out.println("Done!");
		}
		return needToSave;
	}
}
