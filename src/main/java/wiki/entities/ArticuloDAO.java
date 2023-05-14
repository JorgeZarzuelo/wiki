package wiki.entities;


import jakarta.persistence.EntityManager;
import wiki.utils.WikiEntityManager;

public class ArticuloDAO {

	
	
	public Articulo crearArticulo(Articulo articulo) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(articulo);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return articulo;
	}
	
	public Articulo editarArticulo(Articulo _articulo) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Articulo articulo = em.find(Articulo.class, _articulo.getId());
			if (articulo != null) {
				em.merge(_articulo);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return _articulo;
	}
	
	public void eliminarArticuloPorID(Integer id) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Articulo articulo = em.find(Articulo.class, id);
			if (articulo != null) {
				em.remove(articulo);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public Articulo getArticuloByID(Integer id) {
		Articulo currentArticulo = null;
		EntityManager em = WikiEntityManager.getEntityManager();
		try {
			Articulo articulo = em.find(Articulo.class, id);
			if(articulo != null) {				
				currentArticulo = articulo;
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return currentArticulo;
	}
	

	
}