/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import db_objects.Album;
import db_objects.Photo;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author vernet
 */
public class PhotoHelper {
	
	Session session = null;
	
	public PhotoHelper() {
		this.session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
	
	private void checkSession() {
		if (!session.isOpen()) {
			this.session = HibernateUtil.getSessionFactory().getCurrentSession();
		}
	}
	
	public List<Photo> getPhotoList() {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Photo> photoList = null;
		try {
			tx = session.beginTransaction();
			//Query q = session.createQuery("from Message as message");
			//messageList = (List<Message>) q.list();
			photoList = (List<Photo>) session.createCriteria(Photo.class).list();
			for (Photo photo : photoList) {
				Hibernate.initialize(photo.getAlbum());
				Hibernate.initialize(photo.getAlbum().getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return photoList;
	}
	
	public List<Photo> getPhotoList(Album album) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Photo> photoList = null;
		try {
			tx = session.beginTransaction();
			//Query q = session.createQuery("from Message as message");
			//messageList = (List<Message>) q.list();
			photoList = (List<Photo>) session.createCriteria(Photo.class).
							add(Restrictions.eq("album", album)).list();
			for (Photo photo : photoList) {
				Hibernate.initialize(photo.getAlbum());
				Hibernate.initialize(photo.getAlbum().getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return photoList;
	}
	
	public int getPhotoCount() {
		checkSession();
		org.hibernate.Transaction tx = null;
		int photoCount = 0;
		try {
			tx = session.beginTransaction();
			Query q = session.createSQLQuery("select count(*) as count from photo");
			photoCount = ((java.math.BigInteger) q.list().get(0)).intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return photoCount;
	}
	
	public int getMaxPhotoId() {
		checkSession();
		org.hibernate.Transaction tx = null;
		int maxPhotoId = 0;
		try {
			tx = session.beginTransaction();
			Query q = session.createSQLQuery("select max(id) as id "
							+ "from photo");
			maxPhotoId = ((java.math.BigInteger) q.list().get(0)).intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return maxPhotoId;
	}
	
	public int getPhotoCount(Long albumId) {
		checkSession();
		org.hibernate.Transaction tx = null;
		int photoCount = 0;
		try {
			tx = session.beginTransaction();
			Query q = session.createSQLQuery("select count(*) as count "
							+ "from photo where album_id=" + albumId + "");
			photoCount = ((java.math.BigInteger) q.list().get(0)).intValue();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return photoCount;
	}
	
	public List<Photo> getPhotoPage(int start, int count) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Photo> photoList = null;
		try {
			tx = session.beginTransaction();
			photoList = (List<Photo>) session.createCriteria(Photo.class).
							setFirstResult(start).setMaxResults(count).list();
			for (Photo photo : photoList) {
				Hibernate.initialize(photo.getAlbum());
				Hibernate.initialize(photo.getAlbum().getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return photoList;
	}
	
	public List<Photo> getPhotoPage(Long albumId, int start, int count) {
		checkSession();
		org.hibernate.Transaction tx = null;
		List<Photo> photoList = null;
		Album album = new Album();
		album.setId(albumId);
		try {
			tx = session.beginTransaction();
			photoList = (List<Photo>) session.createCriteria(Photo.class).
							add(Restrictions.eq("album", album)).
							setFirstResult(start).setMaxResults(count).list();
			for (Photo photo : photoList) {
				Hibernate.initialize(photo.getAlbum());
				Hibernate.initialize(photo.getAlbum().getUser());
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return photoList;
	}
	
	public void addPhoto(Photo photo) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(photo);
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
	
	public void updatePhoto(Photo photo) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(photo);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}
	
	public void deletePhoto(Photo photo) {
		checkSession();
		org.hibernate.Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(photo);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return;
	}
	
	public Photo getPhotoById(Long id) {
		checkSession();
		org.hibernate.Transaction tx = null;
		Photo photo = null;
		try {
			tx = session.beginTransaction();
			photo = (Photo) session.get(Photo.class, id);
			Hibernate.initialize(photo.getAlbum());
			Hibernate.initialize(photo.getAlbum().getUser());
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			System.err.println(javaoinlibrary.ErrUtils.getStack(new Throwable(), e));
		}
		return photo;
	}
}
