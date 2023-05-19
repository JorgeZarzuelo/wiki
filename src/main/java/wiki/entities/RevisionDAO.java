package wiki.entities;




import jakarta.persistence.EntityManager;
import wiki.managers.WikiEntityManager;

public class RevisionDAO {

	
	
	public Revision crearRevision(Revision revision) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(revision);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return revision;
	}
	

	
	public void eliminarRevision(Revision _revision) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Revision revision = em.find(Revision.class, _revision);
			if (revision != null) {
				em.remove(revision);
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
