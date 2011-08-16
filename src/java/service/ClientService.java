/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db_objects.Message;
import db_objects.User;
import hibernate.MessageHelper;
import hibernate.UserHelper;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.codec.digest.DigestUtils;
import service.session.SessionService;

/**
 *
 * @author vernet
 */
public class ClientService {

	public String Welcome() {
		String s;
		s = "Welcome!";
		return s;
	}

	public List<Message> newMessageList(Long userId, Long lastMessageId) {
		System.out.println("newMessageList IN");
		MessageHelper messageHelper = new MessageHelper();
		List<Message> messages =
						messageHelper.getForClientMessageListNewerThenId(lastMessageId);
		System.out.println("newMessageList OUN");
		return messages;
	}

	public int getLastMessageId() {
		MessageHelper messageHelper = new MessageHelper();
		int lastMessageId = messageHelper.getLastMessageId();
		return lastMessageId;
	}

	public String login(HttpServletRequest hsr,
					String userName, String userPassword) {
		String result = "Reject";
		UserHelper userHelper = new UserHelper();
		User user = userHelper.getUserByName(userName);
		String password = user.getPassword();
		if (password.equals(DigestUtils.md5Hex(userPassword))) {
			SessionService.set(hsr, "clientUserId", "" + user.getId());
			SessionService.set(hsr, "clientUserName", userName);
			SessionService.set(hsr, "clientUserPassword", userPassword);
			result = "Accept";
		} else {
			SessionService.remove(hsr, "clientUserId");
			SessionService.remove(hsr, "clientUserName");
			SessionService.remove(hsr, "clientUserPassword");
		}
		return result;
	}
}
