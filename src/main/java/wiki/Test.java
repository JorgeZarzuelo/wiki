package wiki;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.entities.Wiki;
import wiki.managers.WikiManager;
import wiki.services.HTMLBlockOperationData;
import wiki.services.HTMLComparatorService;

public class Test implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event){
		System.out.println("TEST: Inicializando Test...");
		
		WikiManager manager = new WikiManager();
		
		User coordinador = manager.crearCuenta("coordinador", "1234");
		User supervisor  = manager.crearCuenta("supervisor", "1234");
		User user1 = manager.crearCuenta("user1", "1234");
		
		Wiki wikiJavaEE = manager.crearWiki("JAVA EE", "Todo sobre esta especificacion de java orientada a las aplicaciones empresariales en la web.");
		Wiki wikiJavaSE = manager.crearWiki("JAVA SE", "Java para aplicaciones de escritorio.");
		Wiki wikiHTML = manager.crearWiki("HTML", "Aprende sobre este lenguaje de marcado para páginas web.");
		
		manager.addRolWiki(String.valueOf(coordinador.getId()), Tipo.COORDINADOR.toString(), String.valueOf(wikiJavaEE.getId()));
		manager.addRolWiki(String.valueOf(supervisor.getId()), Tipo.SUPERVISOR.toString(), String.valueOf(wikiHTML.getId()));
		
		manager.crearArticulo(String.valueOf(wikiJavaEE.getId()), "Servlets", "<h1>Los servlets</h1> <p>Procesan peticiones post y get entre otras y delegan normalmente a un archivo jsp la presentación</p>");
		manager.crearArticulo(String.valueOf(wikiJavaEE.getId()), "JSP", "<h1>Vistas con JSP</h1> <p>Forman la capa de presentación del modelo MVC</p>");
		manager.crearArticulo(String.valueOf(wikiJavaSE.getId()), "Intro", "<h1>JAVA SE</h1> <p>Java para aplicaciones de escritorio</p>");
	   
	    manager.solicitarRol(Tipo.SUPERVISOR, "wiki", 1, user1.getId());
	    manager.solicitarRol(Tipo.SUPERVISOR, "articulo", 1, user1.getId());
	    manager.solicitarRol(Tipo.SUPERVISOR, "articulo", 3, user1.getId());
	    
	    manager.creaRevision(user1, "1", "<p>Procesan peticiones post y get entre otras y delegan normalmente a un archivo jsp la presentación</p>");
	    
	    HTMLComparatorService htmlComparator = new HTMLComparatorService();
	    htmlComparator.compare("<p>este es el primero </p><p>este es el segundo</p>", "<p>este es el prime</p><p>este es el segundo</p><p>este es el tercero</p><p>este es el cuarto</p>");
	    int lista[] = {0,1,2,3};
	    htmlComparator.doOperations(lista);
	    
	    Arrays.asList(htmlComparator.getFinalParts()).forEach(part ->{
	    	System.out.println("PARTE:: " + part);
	    });
	    
	    //operaciones.forEach(operacion -> {
	    //	System.out.println("Index f: " + operacion.getFinalPosition() +" Index: i: " + operacion.getInitialPosition() + " Operacion " + operacion.getOperacion() +  " Fragmento antiguo: " + operacion.getHtmlOriginal() + " Fragmento Propuesto " + operacion.getHtmlPropuesto());
	    //});
	    
		System.out.println("TEST: Test finalizados");
	}
}
