package wiki;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import wiki.entities.Articulo;
import wiki.entities.ArticuloDAO;

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
		System.out.println("TEST: Test finalizados");
	}
}
