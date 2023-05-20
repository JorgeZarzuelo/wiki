package wiki.entities;


import java.util.ArrayList;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import wiki.managers.WikiEntityManager;

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
	
	public ArrayList<Articulo> getAllArticulos() {
		ArrayList<Articulo> articulos = null;
		EntityManager em = WikiEntityManager.getEntityManager();		
		try {
			   Query query = em.createQuery("SELECT e from Articulo e", Articulo.class);
			   @SuppressWarnings("unchecked")
			   ArrayList<Articulo> found = (ArrayList<Articulo>) query.getResultList();
			   articulos = found;
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return articulos;
	}
	

	
}
