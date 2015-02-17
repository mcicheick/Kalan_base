/**
 * 
 */
package dd.kalan.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import dd.kalan.container.Container;
import dd.kalan.exceptions.KalanException;
import dd.kalan.models.User;
import dd.kalan.security.Security;

/**
 * @author sissoko
 * @date 16 févr. 2015 17:29:17
 */
public class DatabaseImpl implements InterfaceDatabase, Dao {

	private static EntityManager em;

	/**
	 * 
	 */
	private DatabaseImpl() {
	}

	public static InterfaceDatabase createDatabaseImpl() {
		DatabaseImpl database = new DatabaseImpl();
		Container container;
		try {
			container = new Container(database);
			return container;
		} catch (KalanException e) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#createUser(dd.kalan.models.User)
	 */
	@Override
	public User createUser(User user) throws KalanException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.persist(user);
			user.getId();
			em.getTransaction().commit();
			return user;
		} catch (Exception e) {
			throw new KalanException(e.getCause());
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserById(java.lang.Long)
	 */
	@Override
	public User findUserById(Long id) throws KalanException {
		EntityManager em = getEntityManager();
		try {
			return (User) em.find(User.class, id);
		} catch (Exception e) {
			throw new KalanException(e.getCause());
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#update(dd.kalan.models.User)
	 */
	@Override
	public User updateUser(User user) throws KalanException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			em.merge(user);
			em.getTransaction().commit();
			return user;
		} catch (Exception ex) {
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Long id = user.getId();
				if (findUserById(id) == null) {
					throw new KalanException("The user with id " + id
							+ " no longer exists.");
				}
			}
			throw new KalanException(ex.getCause());
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#deleteUser(java.lang.Long)
	 */
	@Override
	public User deleteUser(Long id) throws KalanException {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			User t;
			t = (User) em.getReference(User.class, id);
			t.getId();
			em.remove(t);
			em.getTransaction().commit();
			return t;
		} catch (Exception enfe) {
			throw new KalanException("The " + User.class.getCanonicalName()
					+ " with id " + id + " no longer exists.", enfe);
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#findUserByPage(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> findUserByPage(Integer page) {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Query query = getEntityManager().createNamedQuery(
					"User.findUserByPage");
			if (page < 1) {
				page = 1;
			}
			query.setFirstResult((page - 1) * Container.USER_PAGE_LENGTH);
			query.setMaxResults(Container.USER_PAGE_LENGTH);
			em.getTransaction().commit();
			return query.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@Override
	public User findUserByUsername(String username) {

		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Query query = getEntityManager().createNamedQuery(
					"User.findUserByUsername").setParameter("username",
					username);
			em.getTransaction().commit();
			return (User) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUser() {
		EntityManager em = null;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Query q = em.createNamedQuery("User.findAllUser");
			em.getTransaction().commit();
			return q.getResultList();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#deleteAllUser()
	 */
	@Override
	public Boolean deleteAllUser() {
		EntityManager em = null;
		Boolean success = false;
		try {
			em = getEntityManager();
			em.getTransaction().begin();
			Query q = em.createNamedQuery("User.deleteAllUser");
			int deleted = q.executeUpdate();
			success = deleted >= 0;
			em.getTransaction().commit();
			return success;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mcicheick.dao.Dao#getEntityManager()
	 */
	@Override
	public EntityManager getEntityManager() {
		if (null == em || !em.isOpen()) {
			em = Emf.getInstance().createEntityManager();
		}
		return em;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#authenticate(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public User authenticate(String username, String password) {
		User user = findUserByUsername(username);
		if (user == null) {
			return null;
		}
		boolean check = Security.checkPassword(user, password);
		if (!check) {
			return null;
		}
		return user;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see dd.kalan.db.InterfaceDatabase#logout()
	 */
	@Override
	public Boolean logout() {
		return true;
	}
}
