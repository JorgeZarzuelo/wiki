package wiki.controllers.usuarios;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wiki.entities.User;
import wiki.managers.WikiManager;

/**
 * Servlet implementation class LoginController
 */
@WebServlet(
		name="LoginController",
		value= {"/login"}
		)
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/login.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (    request.getParameter("username") != null 
				&& !request.getParameter("username").isEmpty() 
				&& request.getParameter("password")  != null 
				&& !request.getParameter("password").isEmpty()) {
			
			WikiManager manager = new WikiManager();
			User currentUser = manager.loginUser(request.getParameter("username"), request.getParameter("password"));
			if (currentUser != null) {
				HttpSession session = request.getSession();	
				
				// Cargamos al usuario en sesión.
				session.setAttribute("user", currentUser);	
				
				// Cargamos en sesion una variable que identifica el rol o roles del usuario
				// El rol de GESTOR se almacena en la entidad User y se crea en el arranque de la aplicación.
				// El resto de roles tienen su propia tabla.
				final boolean isGestor = manager.isUserGestor(currentUser);
				session.setAttribute("isGestor", isGestor);
				final boolean isCoordinador = manager.isUserCoordinador(currentUser);
				session.setAttribute("isCoordinador", isCoordinador);
				final boolean isSupervisor = manager.isUserSupervisor(currentUser);
				session.setAttribute("isSupervisor", isSupervisor);
				final boolean isLoggedIn = true;
				session.setAttribute("isLoggedIn", isLoggedIn);
				
				request.setAttribute("mensaje", "Credenciales correctas");
				RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
				rd.forward(request, response);
				
			} else {
				request.setAttribute("mensaje", "Error: credenciales incorrectas");
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/login.jsp");
				rd.forward(request, response);
			}
			
			
		} else {
			request.setAttribute("mensaje", "Error: introduzca un usuario y una contraseña");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/login.jsp");
			rd.forward(request, response);
		}
		
	}

}
