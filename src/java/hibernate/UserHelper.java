/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import db_objects.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vernet
 */
public class UserHelper {

	Session session = null;

	public UserHelper() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	private void checkSession() {
		if (!session.isOpen()) {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
	}

	public List<User> getUserList() {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<User> userList = null;
		try {
			tx = session.beginTransaction();
			//Query q = session.createQuery("from User as user");
			//userList = (List<User>) q.list();
			userList = (List<User>) session.createCriteria(User.class).list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return userList;
	}

	public void addUser(User user) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public void updateUser(User user) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public void deleteUser(User user) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(user);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public User getUserById(Long id) {
		checkSession();
		org.hibernate.Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.get(User.class, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return user;
	}

	public User getUserByName(String name) {
		checkSession();
		org.hibernate.Transaction tx = null;
		User user = null;
		try {
			tx = session.beginTransaction();
			user = (User) session.createCriteria(User.class).
							add(Restrictions.eq("name", name)).list().get(0);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return user;
	}
}
