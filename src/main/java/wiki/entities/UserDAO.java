package wiki.entities;


import jakarta.persistence.EntityManager;
import wiki.utils.WikiEntityManager;

public class UserDAO {

	
	
	public User crearUser(User user) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(user);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return user;
	}
	
	public User editarUser(User _user) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Articulo articulo = em.find(Articulo.class, _user.getId());
			if (articulo != null) {
				em.merge(_user);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return _user;
	}
	
	public void eliminarUserPorID(Integer id) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			User user = em.find(User.class, id);
			if (user != null) {
				em.remove(user);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public User getUserByID(Integer id) {
		User currentUser = null;
		EntityManager em = WikiEntityManager.getEntityManager();
		try {
			User user = em.find(User.class, id);
			if(user != null) {				
				currentUser = user;
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return currentUser;
	}
	

	
}
