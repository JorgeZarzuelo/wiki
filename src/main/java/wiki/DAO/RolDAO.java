package wiki.DAO;




import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import wiki.entities.Rol;
import wiki.managers.WikiEntityManager;

public class RolDAO {
	
	public void eliminarRolByID(Integer rol_id) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Rol currentRol = em.find(Rol.class, rol_id);			
			if (currentRol != null) {
				em.remove(currentRol);
				em.getTransaction().commit();
			}			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public void deleteAllRolesByWikiId(int wiki_id) {
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();		
		try {
			   Query query = em.createQuery("DELETE FROM Rol r WHERE r.wiki_id = :wiki_id");
			   query.setParameter("wiki_id", wiki_id).executeUpdate();
			   em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		

	}
	
	public ArrayList<Rol> getAllRoles() {
		ArrayList<Rol> roles = null;
		EntityManager em = WikiEntityManager.getEntityManager();		
		try {
			   Query query = em.createQuery("SELECT e from Rol e", Rol.class);
			   @SuppressWarnings("unchecked")
			   ArrayList<Rol> found = (ArrayList<Rol>) query.getResultList();
			   roles = found;
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return roles;
	}

	public Rol getRolByID(Integer id) {
		Rol currentRol = null;
		EntityManager em = WikiEntityManager.getEntityManager();
		try {
			Rol rol = em.find(Rol.class, id);
			if(rol != null) {				
				currentRol = rol;
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return currentRol;
	}
	
	public void deleteRolById(Integer id) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Rol rol = em.find(Rol.class, id);
			if (rol != null) {
				em.remove(rol);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public Rol editarRol(Rol _rol) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Rol rol = em.find(Rol.class, _rol.getId());
			if (rol != null) {
				em.merge(_rol);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return _rol;
	}

	public ArrayList<Rol> getAllRolesByUserId(int id) {
		ArrayList<Rol> roles = null;
		EntityManager em = WikiEntityManager.getEntityManager();		
		try {
			   Query query = em.createQuery("SELECT r FROM Rol r WHERE r.user_id = :id");
			   query.setParameter("id", id);
			   @SuppressWarnings("unchecked")
			   ArrayList<Rol> found = (ArrayList<Rol>) query.getResultList();
			   roles = found;
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return roles;
		
	}

	public void deleteAllRolesByArticuloId(int articulo_id) {
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();		
		try {
			   Query query = em.createQuery("DELETE FROM Rol r WHERE r.articulo_id = :articulo_id");
			   query.setParameter("articulo_id", articulo_id).executeUpdate();
			   em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
	}
	
}
