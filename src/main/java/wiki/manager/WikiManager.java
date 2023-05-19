package wiki.manager;

import java.util.ArrayList;

import wiki.entities.Rol;
import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.entities.UserDAO;
import wiki.entities.Wiki;

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
	
	private void addRol(User user, Rol.Tipo tipo, Wiki wiki) {
		UserDAO userDAO = new UserDAO();
	    user.getRoles().add(new Rol(tipo, false, user.getId(), wiki.getId()));
	}
	
	public boolean isUserGestor(User user) {
		System.out.println("Comprobando si es gestor...");
		System.out.println("ID user: " + user.getId() + ", es admin: " + user.isGestor());
		return user.isGestor();
	}
}
