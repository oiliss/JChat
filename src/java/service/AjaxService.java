/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db_objects.Message;
import hibernate.MessageHelper;
import java.util.List;

/**
 *
 * @author vernet
 */
public class AjaxService {

	public String Welcome() {
		String s;
		s = "Welcome!";
		return s;
	}

	public List<Message> newMessageList(Long userId, Long lastMessageId) {
		MessageHelper messageHelper = new MessageHelper();
		List<Message> messages =
						messageHelper.getMessageListNewerThenId(lastMessageId);
		return messages;
	}

	public int getLastMessageId() {
		MessageHelper messageHelper = new MessageHelper();
		int lastMessageId = messageHelper.getLastMessageId();
		return lastMessageId;
	}
}
