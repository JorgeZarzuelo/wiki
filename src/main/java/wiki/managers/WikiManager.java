package wiki.managers;

import java.util.ArrayList;

import wiki.DAO.ArticuloDAO;
import wiki.DAO.RolDAO;
import wiki.DAO.UserDAO;
import wiki.DAO.WikiDAO;
import wiki.VO.SolicitudVO;
import wiki.entities.Articulo;
import wiki.entities.Rol;
import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.entities.Wiki;
import wiki.utils.Tools;

public class WikiManager {

	public User crearCuenta(String username, String password) {
		UserDAO userDAO = new UserDAO();
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		return userDAO.crearUser(newUser);
	}
	
	public void crearGestor(String username, String password) {
		UserDAO userDAO = new UserDAO();
		User newUser = new User();
		newUser.setUsername(username);
		newUser.setPassword(password);
		newUser.setGestor(true);
		userDAO.crearUser(newUser);
		System.out.println("Creado usuario admin...");	
	}
	
	public User loginUser(String username, String password) {
		UserDAO userDAO = new UserDAO();
		User currentUser = userDAO.getUserByCredentials(username, password);		
		return currentUser;
	}
	
	public void addRolWiki(String user_id, String _tipo, String wiki_id) {
		UserDAO userDAO = new UserDAO();
		Tipo tipo =Tools.getTipoFromString(_tipo);
		User user = userDAO.getUserByID(Integer.parseInt(user_id));
		Rol rol = new Rol();
		rol.setPendiente(false);
		rol.setWiki_id(Integer.parseInt(wiki_id));
		rol.setTipo(tipo);
	    user.getRoles().add(rol);
	    userDAO.editarUser(user);
	    System.out.println("en el manager: id: " + user_id + " UserIDObtenida: " +user.getId() + " roles: " + user.getRoles().size());
	}
	
	public void addRolArticulo(String user_id, String _tipo, String articulo_id) {
		UserDAO userDAO = new UserDAO();
		Tipo tipo =Tools.getTipoFromString(_tipo);
		User user = userDAO.getUserByID(Integer.parseInt(user_id));
		Rol rol = new Rol();
		rol.setPendiente(false);
		rol.setArticulo_id(Integer.parseInt(articulo_id));
		rol.setTipo(tipo);
	    user.getRoles().add(rol);
	    userDAO.editarUser(user);
	    System.out.println("en el manager: id: " + user_id + " UserIDObtenida: " +user.getId() + " roles: " + user.getRoles().size());
	}
	
	public boolean isUserGestor(User user) {
		System.out.println("Comprobando si es gestor...");
		System.out.println("ID user: " + user.getId() + ", es admin: " + user.isGestor());
		return user.isGestor();
	}
	
	public boolean isUserCoordinador(User user) {
		UserDAO userDAO = new UserDAO();
		User current = userDAO.getUserByID(user.getId());		
		return current.getRoles().stream().anyMatch(rol -> rol.getTipo().equals(Tipo.COORDINADOR));		
	}
	
	public boolean isUserSupervisor(User user) {
		UserDAO userDAO = new UserDAO();
		User current = userDAO.getUserByID(user.getId());
		return current.getRoles().stream().anyMatch(rol -> rol.getTipo().equals(Tipo.SUPERVISOR));		
	}
	
	public ArrayList<User> getUserList(){
		ArrayList<User> lista = null;
		UserDAO userDAO = new UserDAO();
		lista = userDAO.getAllUsers();
		lista.removeIf(user -> user.getUsername() == "admin");
		return lista;
	}
	
	public ArrayList<Wiki> getWikisList(){
		ArrayList<Wiki> lista = null;
		WikiDAO wikiDAO = new WikiDAO();
		lista = wikiDAO.getAllWikis();
		return lista;
	}
	
	public ArrayList<Articulo> getArticulosList() {
		ArrayList<Articulo> lista = null;
		ArticuloDAO articulosDAO = new ArticuloDAO();
		lista = articulosDAO.getAllArticulos();
		return lista;
	}
	
	public ArrayList<Rol> getAllRoles() {
		ArrayList<Rol> roles = null;
		RolDAO rolDAO = new RolDAO();
		roles = rolDAO.getAllRoles();
		
		return roles;
	}

	public void eliminarUser(String user_id) {
		UserDAO userDAO = new UserDAO();
		userDAO.eliminarUserPorID(Integer.parseInt(user_id));		
	}

	public void deleteRol(String rol_id) {
		RolDAO rolDAO = new RolDAO();
		rolDAO.eliminarRolByID(Integer.parseInt(rol_id));		
	}
	
	public void editarPassword (String password, String user_id) {		
		UserDAO userDAO = new UserDAO();
		User currentUser = userDAO.getUserByID(Integer.parseInt(user_id));
		currentUser.setPassword(password);
		userDAO.editarUser(currentUser);
	}
	
	public Wiki crearWiki (String topic, String descripcion) {
		Wiki wiki = new Wiki();
		wiki.setTopic(topic);
		wiki.setDescripcion(descripcion);
		WikiDAO wikiDAO = new WikiDAO();
		return wikiDAO.crearWiki(wiki);
	}

	public void editarWiki(String wiki_id, String topic, String descripcion) {
		WikiDAO wikiDAO = new WikiDAO();
		Wiki currentWiki = wikiDAO.getWikiByID(Integer.parseInt(wiki_id));
		currentWiki.setTopic(topic);
		currentWiki.setDescripcion(descripcion);
		wikiDAO.editarWiki(currentWiki);
	}

	public void eliminarWiki(String wiki_id) {
		WikiDAO wikiDAO = new WikiDAO();
		wikiDAO.eliminarWikiPorID(Integer.parseInt(wiki_id));	
		// Eliminamos tambien los roles relacionados con la wiki
		RolDAO rolDAO = new RolDAO();
		rolDAO.deleteAllRolesByWikiId(Integer.parseInt(wiki_id));
	}
	
	public ArrayList<SolicitudVO> getSolicitudesVO() {
		ArrayList<SolicitudVO> solicitudes = null;			
		solicitudes = Tools.populateSolicitudVO(this.getUserList(), this.getWikisList(), this.getArticulosList());
		return solicitudes;
	}

	public void aprobarRol(String rol_id) {
		RolDAO rolDAO = new RolDAO();
		Rol current = rolDAO.getRolByID(Integer.parseInt(rol_id));
		current.setPendiente(false);
		rolDAO.editarRol(current);	    
	}

	public void rechazarRol(String rol_id) {
		RolDAO rolDAO = new RolDAO();
		rolDAO.deleteRolById(Integer.parseInt(rol_id));			
	}

	public ArrayList<Wiki> getCoordinadorWikisList(User currentUser) {
		ArrayList<Wiki> wikis = this.getWikisList();
		ArrayList<Wiki> userWikis = new ArrayList<Wiki>();
		wikis.forEach(wiki -> {
			currentUser.getRoles().forEach( rol -> {
				if (rol.getWiki_id() == wiki.getId()) {
					userWikis.add(wiki);
				}
			});
		});
		return userWikis;
	}
	
	public void crearArticulo(String wiki_id, String titulo, String contenido) {
		Articulo articulo = new Articulo();
		articulo.setTitulo(titulo);
		articulo.setContenido(contenido);
		WikiDAO wikiDAO = new WikiDAO();
		Wiki currentWiki = wikiDAO.getWikiByID(Integer.parseInt(wiki_id));
		currentWiki.getArticulos().add(articulo);
		wikiDAO.editarWiki(currentWiki);		
	}
	
	public void eliminarArticulo(String articulo_id) {
		ArticuloDAO articuloDAO = new ArticuloDAO();
		articuloDAO.eliminarArticuloPorID(Integer.parseInt(articulo_id));
		// eliminamos roles vinculados a este artículo manualmente.
		RolDAO rolDAO = new RolDAO();
		rolDAO.deleteAllRolesByArticuloId(Integer.parseInt(articulo_id));
	}

	public Articulo getArticuloByID(String articulo_id) {
		ArticuloDAO articuloDAO = new ArticuloDAO();
		return (Articulo) articuloDAO.getArticuloByID(Integer.parseInt(articulo_id));		
	}

	public void editarArticulo(String articulo_id, String titulo, String contenido) {
		ArticuloDAO articuloDAO = new ArticuloDAO();
		Articulo currentArticulo = articuloDAO.getArticuloByID(Integer.parseInt(articulo_id));
		currentArticulo.setTitulo(titulo);
		currentArticulo.setContenido(contenido);
		articuloDAO.editarArticulo(currentArticulo);
		
	}







	


	
}
