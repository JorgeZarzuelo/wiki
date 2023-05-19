package wiki;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import wiki.entities.Articulo;
import wiki.entities.ArticuloDAO;
import wiki.entities.Rol;
import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.entities.UserDAO;
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
	    
	    
	    //USUARIOS
	    User usuario = new User();
	    UserDAO userDAO = new UserDAO();
	    usuario.setUsername("pepe");
	    usuario.setPassword("ASDFASDFA343.#");
	    User createdUser = userDAO.crearUser(usuario);
	    
	    User usuario2 = new User();
	    usuario2.setUsername("juan");
	    usuario2.setPassword("ASasdfasdf343.#");
	    User createdUser2 = userDAO.crearUser(usuario2);
	    
	    Rol rol1 = new Rol();
	    rol1.setTipo(Tipo.COORDINADOR);
	    
	    rol1.setWiki_id(wikiGuardada.getId());
	    //rol1.setUser_id(createdUser.getId());
	    createdUser.getRoles().add(rol1);     
	    	       
	    
	    
	    Rol rol2 = new Rol();
	    rol2.setTipo(Tipo.SUPERVISOR);
	    rol2.setWiki_id(wikiGuardada.getId());
	    //rol2.setUser_id(createdUser.getId());
	    createdUser.getRoles().add(rol2); 
	    
	    userDAO.editarUser(createdUser);
	    
	    createdUser2.getRoles().add(rol2); 
	    
	    userDAO.editarUser(createdUser2);
	       
	    User user1 = userDAO.getUserByID(createdUser.getId());
	    User user2 = userDAO.getUserByID(createdUser2.getId());
	    
	    user1.getRoles().forEach(rol -> {
	    	 System.out.println("TEST: Roles usuario editado: " + rol.getTipo() + " ROL ID: " + rol.getId());
	    });
	    
	    user2.getRoles().forEach(rol -> {
	    	 System.out.println("TEST: Roles usuario editado: " + rol.getTipo() + " ROL ID: " + rol.getId());
	    });
	   
	    
	    
		System.out.println("TEST: Test finalizados");
	}
}
