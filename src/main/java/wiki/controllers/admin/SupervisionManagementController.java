package wiki.controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.VO.ConcreteSupervisionVO;
import wiki.entities.User;
import wiki.managers.WikiManager;

/**
 * Servlet implementation class SupervisionesManagementController
 */
@WebServlet(
		name="SupervisionManagementController",
		value= {"/revision"}
		)
public class SupervisionManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupervisionManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		if(    request.getParameter("operacion") != null 
				&& !request.getParameter("operacion").isEmpty()) 
		{
			
			String operacion = request.getParameter("operacion");
			
			switch(operacion) {
			case "revisar":{
				
				if (    request.getParameter("revision_id") != null 
						&& !request.getParameter("revision_id").isEmpty() 
						&& request.getParameter("user_id")  != null 
						&& !request.getParameter("user_id").isEmpty()) {
					
					WikiManager manager = new WikiManager();
					ConcreteSupervisionVO supervision = manager.getConcreteSupervisionVO(Integer.parseInt(request.getParameter("user_id")), Integer.parseInt(request.getParameter("revision_id")));
					request.setAttribute("supervision", supervision);
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/admin/revision.jsp");	
					rd.forward(request, response);
					
				}else {
					
					request.setAttribute("mensaje", "Error: Parámetros incorrectos");						
					doGet(request, response);
				}	
			} break;
			
			}
			
		}else {
			request.setAttribute("mensaje", "Error: fallo al enviar el formulario");			
			doGet(request, response);
		}
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
			case "revision":{
				
				if (    request.getParameter("revision_id") != null 
						&& !request.getParameter("revision_id").isEmpty() 
						&& request.getParameter("user_id")  != null 
						&& !request.getParameter("user_id").isEmpty()) {
					
					RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/admin/revision.jsp");	
					rd.forward(request, response);
					
				}else {
					request.setAttribute("mensaje", "Error: Parámetros incorrectos");						
					doGet(request, response);
				}	
			} break;
			
			case "rechazar":{
				WikiManager manager = new WikiManager();
				if (    request.getParameter("revision_id") != null 
						&& !request.getParameter("revision_id").isEmpty() 
						&& request.getParameter("user_id")  != null 
						&& !request.getParameter("user_id").isEmpty()) {
					
					manager.eliminarRevision(Integer.parseInt(request.getParameter("revision_id")));
					request.setAttribute("mensaje", "Solicitud de modificación rechazada");						
					doGet(request, response);
				}else {
					request.setAttribute("mensaje", "Error: Parámetros incorrectos");						
					doGet(request, response);
				}	
			} break;
			
			} // fin del switch
			
			
			
		} else {
			request.setAttribute("mensaje", "Error: fallo al enviar el formulario");			
			doGet(request, response);
		}
	}

}
