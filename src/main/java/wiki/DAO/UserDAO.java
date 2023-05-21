package wiki.DAO;


import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import wiki.entities.User;
import wiki.managers.WikiEntityManager;

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
			User user = em.find(User.class, _user.getId());
			if (user != null) {
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
	
	public User getUserByCredentials (String username, String password) {
		User currentUser = null;
		EntityManager em = WikiEntityManager.getEntityManager();
		try {
			Query query = em.createQuery("SELECT u from User u WHERE u.username=:username AND u.password=:password");
			query.setParameter("username", username);
			query.setParameter("password", password);
			currentUser = (User) query.getSingleResult();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		return currentUser;
	}
	
	public ArrayList<User> getAllUsers(){
		ArrayList<User> users = null;
		EntityManager em = WikiEntityManager.getEntityManager();		
		try {
			   Query query = em.createQuery("SELECT e from User e", User.class);
			   @SuppressWarnings("unchecked")
			   ArrayList<User> foundUsers = (ArrayList<User>) query.getResultList();
		       users = foundUsers;
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return users;
	}
	

	
}
