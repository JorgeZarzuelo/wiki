package wiki.controllers.usuarios;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.manager.WikiManager;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet(
		name="RegisterController",
		value= {"/registro"}
		)
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/registro.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WikiManager manager = new WikiManager();
		if (    request.getParameter("username") != null 
				&& !request.getParameter("username").isEmpty() 
				&& request.getParameter("password")  != null 
				&& !request.getParameter("password").isEmpty()) {
			
			manager.crearCuenta(request.getParameter("username"), request.getParameter("password"));
			request.setAttribute("mensaje", "usario creado con éxito, inicie sesión");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("mensaje", "Error: introduzca un usuario y una contraseña");
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/registro.jsp");
			rd.forward(request, response);
		}		
		
	}

}
