package wiki.DAO;




import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import wiki.entities.Articulo;
import wiki.entities.Revision;
import wiki.entities.Rol;
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
	
	public ArrayList<Revision> getAllRevisiones() {
		ArrayList<Revision> revisiones = null;
		EntityManager em = WikiEntityManager.getEntityManager();		
		try {
			   Query query = em.createQuery("SELECT e from Revision e", Revision.class);
			   @SuppressWarnings("unchecked")
			   ArrayList<Revision> found = (ArrayList<Revision>) query.getResultList();
			   revisiones = found;
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return revisiones;
	}
	
	public void eliminarRevisionByID(Integer revision_id) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Revision currentRevision = em.find(Revision.class, revision_id);			
			if (currentRevision != null) {
				em.remove(currentRevision);
				em.getTransaction().commit();
			}			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public Revision getRevisionByID(Integer id) {
		Revision currentRevision = null;
		EntityManager em = WikiEntityManager.getEntityManager();
		try {
			Revision revision = em.find(Revision.class, id);
			if(revision != null) {				
				currentRevision = revision;
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return currentRevision;
	}
	

	
}
