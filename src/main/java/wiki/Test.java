package wiki;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import wiki.DAO.ArticuloDAO;
import wiki.DAO.UserDAO;
import wiki.DAO.WikiDAO;
import wiki.entities.Articulo;
import wiki.entities.Rol;
import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.entities.Wiki;
import wiki.managers.WikiManager;

public class Test implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event){
		System.out.println("TEST: Inicializando Test...");
		
		WikiManager manager = new WikiManager();
		
		User coordinador = manager.crearCuenta("coordinador", "1234");
		User supervisor  = manager.crearCuenta("supervisor", "1234");
		
		Wiki wikiJavaEE = manager.crearWiki("JAVA EE", "Todo sobre esta especificacion de java orientada a las aplicaciones empresariales en la web.");
		Wiki wikiJavaSE = manager.crearWiki("JAVA SE", "Java para aplicaciones de escritorio.");
		Wiki wikiHTML = manager.crearWiki("HTML", "Aprende sobre este lenguaje de marcado para páginas web.");
		
		manager.addRolWiki(String.valueOf(coordinador.getId()), Tipo.COORDINADOR.toString(), String.valueOf(wikiJavaEE.getId()));
		manager.addRolWiki(String.valueOf(supervisor.getId()), Tipo.SUPERVISOR.toString(), String.valueOf(wikiHTML.getId()));
		
		manager.crearArticulo(String.valueOf(wikiJavaEE.getId()), "Servlets", "<h1>Los servlets</h1> <p>Procesan peticiones post y get entre otras y delegan normalmente a un archivo jsp la presentación</p>");
		manager.crearArticulo(String.valueOf(wikiJavaEE.getId()), "JSP", "<h1>Vistas con JSP</h1> <p>Forman la capa de presentación del modelo MVC</p>");
	   
	    
	    
		System.out.println("TEST: Test finalizados");
	}
}
