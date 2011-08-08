/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.entity.Link;
import db_objects.Message;
import db_objects.User;
import hibernate.MessageHelper;
import hibernate.UserHelper;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vernet
 */
public class HistoryService {

	public String Welcome() {
		String s;
		s = "Welcome!";
		return s;
	}

	public List<Message> messageList(Long userId, int pageNum) {
		UserHelper userHelper = new UserHelper();
		MessageHelper messageHelper = new MessageHelper();
		User user = userHelper.getUserById(userId);
		int messageCount = messageHelper.getMessageCount();
		short messagesOnPage = user.getMessagesOnPage();
		if (messagesOnPage < 1) {
			messagesOnPage = 1;
		}
		List<Message> messages =
						messageHelper.getMessagePageDesc(
						(pageNum - 1) * messagesOnPage, messagesOnPage);
		return messages;
	}

	public List<Link> linkList(Long userId, int pageNum) {
		UserHelper userHelper = new UserHelper();
		MessageHelper messageHelper = new MessageHelper();
		User user = userHelper.getUserById(userId);
		int messageCount = messageHelper.getMessageCount();
		short messagesOnPage = user.getMessagesOnPage();
		if (messagesOnPage < 1) {
			messagesOnPage = 1;
		}
		List<Link> linkList = new ArrayList();
		Link link;
		int linkPageCount = (int) Math.ceil((double) messageCount / messagesOnPage);
		int linkPageNum = 0;
		String url = "/history.htm?page_num=";

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
