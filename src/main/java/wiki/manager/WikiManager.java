package wiki.manager;

import java.util.ArrayList;

import wiki.entities.Rol;
import wiki.entities.Rol.Tipo;
import wiki.entities.RolDAO;
import wiki.entities.User;
import wiki.entities.UserDAO;
import wiki.entities.Wiki;
import wiki.entities.WikiDAO;
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
	
	public void addRol(String user_id, String _tipo, String wiki_id) {
		UserDAO userDAO = new UserDAO();
		Tipo tipo =Tools.getTipoFromString(_tipo);
		User user = userDAO.getUserByID(Integer.parseInt(user_id));
	    user.getRoles().add(new Rol(tipo, false, Integer.parseInt(user_id), Integer.parseInt(wiki_id)));
	    userDAO.editarUser(user);
	}
	
	public boolean isUserGestor(User user) {
		System.out.println("Comprobando si es gestor...");
		System.out.println("ID user: " + user.getId() + ", es admin: " + user.isGestor());
		return user.isGestor();
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


	
}