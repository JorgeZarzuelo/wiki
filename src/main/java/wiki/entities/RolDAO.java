package wiki.entities;




import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
	
}
