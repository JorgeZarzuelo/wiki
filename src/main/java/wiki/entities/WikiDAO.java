package wiki.entities;


import jakarta.persistence.EntityManager;
import wiki.utils.WikiEntityManager;

public class WikiDAO {

	
	
	public Wiki crearWiki(Wiki wiki) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			em.persist(wiki);
			em.getTransaction().commit();
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		return wiki;
	}
	
	public Wiki editarWiki(Wiki _wiki) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Wiki wiki = em.find(Wiki.class, _wiki.getId());
			if (wiki != null) {
				em.merge(_wiki);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
		
		return _wiki;
	}
	
	public void eliminarWikiPorID(Integer id) {
		
		EntityManager em = WikiEntityManager.getEntityManager();
		em.getTransaction().begin();
		try {
			Wiki wiki = em.find(Wiki.class, id);
			if (wiki != null) {
				em.remove(wiki);
				em.getTransaction().commit();
			}
			
		}catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}
	
	public Wiki getWikiByID(Integer id) {
		Wiki currentWiki = null;
		EntityManager em = WikiEntityManager.getEntityManager();
		try {
			Wiki wiki = em.find(Wiki.class, id);
			if(wiki != null) {				
				currentWiki = wiki;
			}
		} catch (Exception e) {
			e.printStackTrace();			
		} finally {
			em.close();
		}
		
		return currentWiki;
	}
	

	
}
