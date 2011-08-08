/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import db_objects.Message;
import java.util.Date;
import java.util.List;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;

/**
 *
 * @author vernet
 */
public class MessageHelper {

	Session session = null;

	public MessageHelper() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	private void checkSession() {
		if (!session.isOpen()) {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
	}

	public List<Message> getMessageList() {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Message> messageList = null;
		try {
			tx = session.beginTransaction();
			//Query q = session.createQuery("from Message as message");
			//messageList = (List<Message>) q.list();
			messageList = (List<Message>) session.createCriteria(Message.class).list();
			for (Message message : messageList) {
				Hibernate.initialize(message.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageList;
	}

	public int getMessageCount() {
		checkSession();
		org.hibernate.Transaction tx = null;
		int messageCount = 0;
		try {
			tx = session.beginTransaction();
			Query q = session.createSQLQuery("select count(*) as count from message");
			messageCount = ((java.math.BigInteger) q.list().get(0)).intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageCount;
	}

	public int getLastMessageId() {
		checkSession();
		org.hibernate.Transaction tx = null;
		int messageCount = 0;
		try {
			tx = session.beginTransaction();
			Query q = session.createSQLQuery("select max(id) as maxid from message");
			messageCount = ((java.math.BigInteger) q.list().get(0)).intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageCount;
	}

	public List<Message> getMessagePage(int start, int count) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Message> messageList = null;
		try {
			tx = session.beginTransaction();
			messageList = (List<Message>) session.createCriteria(Message.class).
							setFirstResult(start).setMaxResults(count).list();
			for (Message message : messageList) {
				Hibernate.initialize(message.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageList;
	}

	public List<Message> getMessagePageDesc(int start, int count) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Message> messageList = null;
		try {
			tx = session.beginTransaction();
			messageList = (List<Message>) session.createCriteria(Message.class).
							addOrder(Order.desc("id")).
							setFirstResult(start).setMaxResults(count).list();
			for (Message message : messageList) {
				Hibernate.initialize(message.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageList;
	}

	public List<Message> getNewMessageList(Date date, short messagesOnPage) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Message> messageList = null;
		try {
			tx = session.beginTransaction();
			messageList = (List<Message>) session.createCriteria(Message.class).
							add(Expression.gt("date", date)).
							setMaxResults(messagesOnPage).list();
			for (Message message : messageList) {
				Hibernate.initialize(message.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageList;
	}

	public List<Message> getNewMessageListDesc(Date date, short messagesOnPage) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Message> messageList = null;
		try {
			tx = session.beginTransaction();
			messageList = (List<Message>) session.createCriteria(Message.class).
							addOrder(Order.desc("id")).
							add(Expression.gt("date", date)).
							list();
			int newMessagesCount = messageList.size();
			if (newMessagesCount < messagesOnPage) {
				messageList.addAll(
								(List<Message>) session.createCriteria(Message.class).
								addOrder(Order.desc("id")).
								setFirstResult(newMessagesCount).
								setMaxResults(messagesOnPage - newMessagesCount).
								list());
			}
			for (Message message : messageList) {
				Hibernate.initialize(message.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageList;
	}

	public List<Message> getMessageListNewerThenId(Long lastMessageId) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Message> messageList = null;
		try {
			tx = session.beginTransaction();
			messageList = (List<Message>) session.createCriteria(Message.class).
							addOrder(Order.desc("id")).
							add(Expression.gt("id", lastMessageId)).
							list();
			for (Message message : messageList) {
				Hibernate.initialize(message.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return messageList;
	}

	public void addMessage(Message message) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(message);
			tx.commit();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public void updateMessage(Message message) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(message);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public void deleteMessage(Message message) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(message);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public Message getMessageById(Long id) {
		checkSession();
		org.hibernate.Transaction tx = null;
		Message message = null;
		try {
			tx = session.beginTransaction();
			message = (Message) session.get(Message.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return message;
	}
}
