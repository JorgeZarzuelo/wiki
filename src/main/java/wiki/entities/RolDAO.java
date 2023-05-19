package wiki.entities;




import jakarta.persistence.EntityManager;
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
	
}
