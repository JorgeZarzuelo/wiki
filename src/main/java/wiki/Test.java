package wiki;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import wiki.entities.Articulo;
import wiki.entities.ArticuloDAO;
import wiki.entities.Wiki;
import wiki.entities.WikiDAO;

public class Test implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event){
		System.out.println("TEST: Inicializando Test...");
		
		ArticuloDAO articuloDAO = new ArticuloDAO();
		Articulo currentArticulo = articuloDAO.crearArticulo(new Articulo("este es mi primer titulo", "esto es el contenido wow" ));
	    System.out.println("TEST: articulo introducido: "+ currentArticulo.getTitulo());
		Articulo articuloBuscado = articuloDAO.getArticuloByID(currentArticulo.getId());
		System.out.println("TEST: articulo buscado: "+ articuloBuscado.getTitulo());
		articuloBuscado.setTitulo("Este es mi titulo MODIFICADO");
		articuloDAO.editarArticulo(articuloBuscado);
		Articulo articuloModificado = articuloDAO.getArticuloByID(articuloBuscado.getId());
		System.out.println("TEST: articulo modificado: "+ articuloModificado.getTitulo());
		
		
		//Wikis
		WikiDAO wikiDAO = new WikiDAO();
	    Wiki wiki = new Wiki("conejos", "Los conejos mas buscados de españa");
	    Articulo articuloWiki = new Articulo("El maravilloso mundo de los conejos", "esto es el contenido conejeril" );
	    wiki.getArticulos().add(articuloWiki);
	    Articulo articuloWiki2 = new Articulo("Los conejos mas importantes", "esto es el contenido conejeril importante" );
	    wiki.getArticulos().add(articuloWiki2);
	    
	    Wiki wikiGuardada = wikiDAO.crearWiki(wiki);
	    
	    Articulo articuloWiki3 = articuloDAO.crearArticulo(new Articulo("Conejos a mitad de precio", "<b>increible oferta en conejos</b>" ));
	    wikiGuardada.getArticulos().add(articuloWiki3);
	    
	    
	    System.out.println("TEST: creada wiki y añadido articulo anterior: " + wikiGuardada.getTopic());
		//recorrer articulos de una wiki
	    wikiGuardada.getArticulos().forEach((articulo) -> {
	    	System.out.println("TEST: Recorriendo articulos: " + articulo.getTitulo() + " Contenido: " + articulo.getContenido());
	    });
		System.out.println("TEST: Test finalizados");
	}
}
