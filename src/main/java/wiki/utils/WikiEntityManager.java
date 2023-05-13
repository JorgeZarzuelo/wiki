package wiki.utils;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class WikiEntityManager {
	
	public WikiEntityManager() {}
	
    private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEmf() {
		return emf;
	}
	
	public static void setEmf(EntityManagerFactory emf) {
		WikiEntityManager.emf = emf;
	}
	
	public void crear() {
		// Creación del entity manager factory
		System.out.println("Creando entity manager factory...");
	    emf = Persistence.createEntityManagerFactory("PU_JPA");
	}
	
	public void destruir() {
		// Destrucción del entity manager factory	 
		System.out.println("Destruyendo entity manager factory...");
		if (emf != null && emf.isOpen()) {
			emf.close();
		}
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	
}
