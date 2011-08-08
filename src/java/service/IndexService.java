/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import db_objects.Message;
import db_objects.User;
import hibernate.MessageHelper;
import hibernate.UserHelper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author vernet
 */
public class IndexService {

	public void addMessage(Long userId, String text) {
		Message message = new Message();
		User user = new User();
		user.setId(userId);
		message.setUser(user);
		message.setText(text);
		message.setDate(new Date());
		MessageHelper messageHelper = new MessageHelper();
		messageHelper.addMessage(message);
		return;
	}

	public int getLastMessageId() {
		MessageHelper messageHelper = new MessageHelper();
		int lastMessageId = messageHelper.getLastMessageId();
		return lastMessageId;	
	}

	public List<Message> messageList(Long userId) {
		UserHelper userHelper = new UserHelper();
		MessageHelper messageHelper = new MessageHelper();
		User user = userHelper.getUserById(userId);
		Date shownDate = user.getShownDate();
		if (shownDate == null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
			try {
				shownDate = format.parse("2011.01.01 00:00:00");
			} catch (ParseException ex) {
				shownDate = new Date();
			}
		}
		short messagesOnPage = user.getMessagesOnPage();
		List<Message> messages =
						messageHelper.getNewMessageListDesc(shownDate, messagesOnPage);
		//List<Message> messages =
		//				messageHelper.getMessageList();
		if (messages != null) {
			if (messages.size() > 0) {
				for (Message message : messages) {
					shownDate = message.getDate();
				}
			} else {
				shownDate = new Date();
			}
		} else {
			shownDate = new Date();
		}
		user.setShownDate(shownDate);

		/**
		 * [ Пятница, Август, 2011.08.05 09:37:35 Овчарик Игорь (oin) ]
		 * E-Mail: in.oiliss@gmail.com, ICQ: 262-345-645, Skype: oiliss
		 * Дату последнего просмотра, по которой определяем уже
		 * просмотренные сообщения, автоматически не меняем при показе
		 * сообщений на странице.
		 * Теперь это будет делаться в отдельном месте по нажатию
		 * пользователем клавиши "Все эти сообщения я уже прочитал".
		 */
		//userHelper.updateUser(user);
		return messages;
	}

	public void changeShownDate(Long userId, Long messageId) {
		UserHelper userHelper = new UserHelper();
		MessageHelper messageHelper = new MessageHelper();
		User user = userHelper.getUserById(userId);
		Message message = messageHelper.getMessageById(messageId);
		user.setShownDate(message.getDate());
		userHelper.updateUser(user);
		return;
	}
}
