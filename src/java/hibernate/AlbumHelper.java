/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import db_objects.Album;
import db_objects.User;
import java.util.List;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vernet
 */
public class AlbumHelper {

	Session session = null;

	public AlbumHelper() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}

	private void checkSession() {
		if (!session.isOpen()) {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
	}

	public List<Album> getAlbumList() {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Album> albumList = null;
		try {
			tx = session.beginTransaction();
			//Query q = session.createQuery("from Message as message");
			//messageList = (List<Message>) q.list();
			albumList = (List<Album>) session.createCriteria(Album.class).list();
			for (Album album : albumList) {
				Hibernate.initialize(album.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return albumList;
	}

	public List<Album> getAlbumList(User user) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Album> albumList = null;
		try {
			tx = session.beginTransaction();
			//Query q = session.createQuery("from Message as message");
			//messageList = (List<Message>) q.list();
			albumList = (List<Album>) session.createCriteria(Album.class).
							add(Restrictions.eq("user", user)).list();
			for (Album album : albumList) {
				Hibernate.initialize(album.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return albumList;
	}

	public int getAlbumCount() {
		checkSession();
		org.hibernate.Transaction tx = null;
		int albumCount = 0;
		try {
			tx = session.beginTransaction();
			Query q = session.createSQLQuery("select count(*) as count from album");
			albumCount = ((java.math.BigInteger) q.list().get(0)).intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return albumCount;
	}

	public int getAlbumCount(Long userId) {
		checkSession();
		org.hibernate.Transaction tx = null;
		int albumCount = 0;
		try {
			tx = session.beginTransaction();
			Query q = session.createSQLQuery("select count(*) as count "
							+ "from album where user_id=" + userId + "");
			albumCount = ((java.math.BigInteger) q.list().get(0)).intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return albumCount;
	}

	public List<Album> getAlbumPage(int start, int count) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Album> albumList = null;
		try {
			tx = session.beginTransaction();
			albumList = (List<Album>) session.createCriteria(Album.class).
							setFirstResult(start).setMaxResults(count).list();
			for (Album album : albumList) {
				Hibernate.initialize(album.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return albumList;
	}

	public List<Album> getAlbumPage(Long userId, int start, int count) {
		System.out.println("userId = " + userId);
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Album> albumList = null;
		User user = new User();
		user.setId(userId);
		try {
			tx = session.beginTransaction();
			albumList = (List<Album>) session.createCriteria(Album.class).
							add(Restrictions.eq("user", user)).
							setFirstResult(start).setMaxResults(count).list();
			for (Album album : albumList) {
				Hibernate.initialize(album.getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return albumList;
	}

	public void addAlbum(Album album) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(album);
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

	public void updateAlbum(Album album) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(album);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public void deleteAlbum(Album album) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(album);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}

	public Album getAlbumById(Long id) {
		checkSession();
		org.hibernate.Transaction tx = null;
		Album album = null;
		try {
			tx = session.beginTransaction();
			album = (Album) session.get(Album.class, id);
			Hibernate.initialize(album.getUser());
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return album;
	}
}
