package wiki;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import wiki.managers.WikiEntityManager;
import wiki.managers.WikiManager;
/**
 * 
 * @author JORGE ZARZUELO GUTIERREZ
 *
 */
public class InitApp implements ServletContextListener{
	
	private WikiEntityManager wikiEntityManager = new WikiEntityManager();	
	
	
	public void contextInitialized(ServletContextEvent event){
		System.out.println("Inicializando Aplicación...");
		
		// Creación del entity manager factory
		wikiEntityManager.crear();
		
		// Creación del usuario GESTOR
		WikiManager manager = new WikiManager();
		manager.crearGestor("admin", "admin");
		
		System.out.println("Aplicación inicializada");
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("Cerrando Aplicación...");
		
		// Destrucción del entity manager factory
		wikiEntityManager.destruir();
		
		System.out.println("Aplicación finalizada");
		
	}
}
