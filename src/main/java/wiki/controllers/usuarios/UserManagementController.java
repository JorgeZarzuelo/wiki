package wiki.controllers.usuarios;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.managers.WikiManager;

/**
 * Servlet implementation class UserManagementController
 */
@WebServlet(
		name="UserManagementController",
		value= {"/users"}
		)
public class UserManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		WikiManager manager = new WikiManager();
		request.setAttribute("users", manager.getUserList());
		request.setAttribute("wikis", manager.getWikisList());
		request.setAttribute("articulos", manager.getArticulosList());
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/admin/users.jsp");
		rd.forward(request, response);
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(    request.getParameter("operacion") != null 
				&& !request.getParameter("operacion").isEmpty()) 
		{
			String operacion = request.getParameter("operacion");
			switch(operacion) {
			    //creacion de nuevo usuario
				case "crearUsuario":{
					WikiManager manager = new WikiManager();
					if (    request.getParameter("username") != null 
							&& !request.getParameter("username").isEmpty() 
							&& request.getParameter("password")  != null 
							&& !request.getParameter("password").isEmpty()) {
						
						manager.crearCuenta(request.getParameter("username"), request.getParameter("password"));
						request.setAttribute("mensaje", "usario creado con éxito");						
						doGet(request, response);
					}else {
						request.setAttribute("mensaje", "Error: introduzca un usuario y una contraseña");						
						doGet(request, response);
					}	
				} break;
				// añadir rol
				case "addRoleWiki":{
					if (    request.getParameter("id_usuario") != null 
							&& !request.getParameter("id_usuario").isEmpty() 
							&& request.getParameter("rol")  != null 
							&& !request.getParameter("rol").isEmpty()
							&& request.getParameter("wiki_id")  != null 
							&& !request.getParameter("wiki_id").isEmpty()){
						WikiManager manager = new WikiManager();
						manager.addRolWiki(
								request.getParameter("id_usuario"), 
								request.getParameter("rol"), 
								request.getParameter("wiki_id"));
						request.setAttribute("mensaje", "Añadido rol");
						doGet(request, response);
					}
				} break;
				// añadir rol
				case "addRoleArticulo":{
					if (    request.getParameter("id_usuario") != null 
							&& !request.getParameter("id_usuario").isEmpty() 
							&& request.getParameter("rol")  != null 
							&& !request.getParameter("rol").isEmpty()
							&& request.getParameter("articulo_id")  != null 
							&& !request.getParameter("articulo_id").isEmpty()){
						WikiManager manager = new WikiManager();
						manager.addRolArticulo(
								request.getParameter("id_usuario"), 
								request.getParameter("rol"), 
								request.getParameter("articulo_id"));
						request.setAttribute("mensaje", "Añadido rol");
						doGet(request, response);
					}
				} break;
				// eliminar rol
				case "deleteRol":{
					if (    request.getParameter("rol_id") != null 
							&& !request.getParameter("rol_id").isEmpty() ) {
						WikiManager manager = new WikiManager();
						manager.deleteRol(request.getParameter("rol_id"));
						request.setAttribute("mensaje", "Rol eliminado");						
						doGet(request, response);
					}
				} break;
				// eliminar usuario
				case "deleteUser":{
					if (    request.getParameter("user_id") != null 
							&& !request.getParameter("user_id").isEmpty() ) {
						WikiManager manager = new WikiManager();
						manager.eliminarUser(request.getParameter("user_id"));
						request.setAttribute("mensaje", "Usuario eliminado");						
						doGet(request, response);
					}
				} break;
				// editar contraseña
				case "editUser":{
					if (    request.getParameter("user_id") != null 
							&& !request.getParameter("user_id").isEmpty() 
							&& request.getParameter("password") != null 
							&& !request.getParameter("password").isEmpty()	) {
						WikiManager manager = new WikiManager();						
						manager.editarPassword(request.getParameter("password"), request.getParameter("user_id"));						
						request.setAttribute("mensaje", "Contraseña cambiada");						
						doGet(request, response);
					}
				} break;
				
			}
		} else {
			request.setAttribute("mensaje", "Error: fallo al enviar el formulario");			
			doGet(request, response);
		}	
			
		
		
		
	}

}
