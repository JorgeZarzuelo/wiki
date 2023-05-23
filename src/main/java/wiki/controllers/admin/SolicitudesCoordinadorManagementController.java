package wiki.controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.entities.User;
import wiki.managers.WikiManager;

/**
 * Servlet implementation class WikisManagementController
 */
@WebServlet(
		name="SolicitudesCoordinadorManagementController",
		value= {"/solicitudes_coordinador"}
		)
public class SolicitudesCoordinadorManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SolicitudesCoordinadorManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WikiManager manager = new WikiManager();		
		request.setAttribute("solicitudes", manager.getSolicitudesCoordinadorVO( (User) request.getSession().getAttribute("user")));
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/admin/solicitudes_coordinador.jsp");	
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
			
			case "aprobar":{
				if (    request.getParameter("rol_id") != null 
						&& !request.getParameter("rol_id").isEmpty()						
						) {
					WikiManager manager = new WikiManager();
					manager.aprobarRol(request.getParameter("rol_id"));
					request.setAttribute("mensaje", "Rol Aprobado");	
					
					doGet(request, response);
				} else {
					request.setAttribute("mensaje", "Error: Ha ocurrido un error");
					doGet(request, response);
				}
			} break;
			case "rechazar":{
				if (    request.getParameter("rol_id") != null 
						&& !request.getParameter("rol_id").isEmpty()						
						) {
					WikiManager manager = new WikiManager();
					manager.rechazarRol(request.getParameter("rol_id"));
					request.setAttribute("mensaje", "Rol Rechazado");	
					
					doGet(request, response);
				} else {
					request.setAttribute("mensaje", "Error: Ha ocurrido un error");
					doGet(request, response);
				}
			} break;
			
			
			
			}//fin del switch
			
		} else {
			request.setAttribute("mensaje", "Error: fallo al enviar el formulario");			
			doGet(request, response);
		}	
	}

}
