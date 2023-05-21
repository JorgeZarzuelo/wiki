package wiki.controllers.admin;

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
 * Servlet implementation class ArticulosManagementController
 */
@WebServlet(
		name="ArticulosManagementController",
		value= {"/articulos"}
		)
public class ArticulosManagementController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArticulosManagementController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WikiManager manager = new WikiManager();
		request.setAttribute("wikis", manager.getCoordinadorWikisList((User) request.getSession().getAttribute("user")));
				
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/admin/articulos.jsp");	
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
			
			case "crear":{
				if (    request.getParameter("wiki_id") != null 
						&& !request.getParameter("wiki_id").isEmpty()
						&& request.getParameter("titulo") != null 
						&& !request.getParameter("titulo").isEmpty()
						&& request.getParameter("contenido") != null 
						&& !request.getParameter("contenido").isEmpty()
						) {
					WikiManager manager = new WikiManager();
					manager.crearArticulo(request.getParameter("wiki_id"), request.getParameter("titulo"), request.getParameter("contenido"));
					request.setAttribute("mensaje", "Artículo creado");	
					
					doGet(request, response);
				} else {
					request.setAttribute("mensaje", "Error: Debe introducir un título y descripción");
					doGet(request, response);
				}
			} break;
			
			case "editar":{
				
				if (    request.getParameter("topic") != null 
						&& !request.getParameter("topic").isEmpty()
						&& request.getParameter("descripcion") != null 
						&& !request.getParameter("descripcion").isEmpty()
						&& request.getParameter("wiki_id") != null 
						&& !request.getParameter("wiki_id").isEmpty()
						) {
					WikiManager manager = new WikiManager();
					manager.editarWiki(request.getParameter("wiki_id"), request.getParameter("topic"), request.getParameter("descripcion"));
					request.setAttribute("mensaje", "Wiki editada");	
					
					doGet(request, response);
				} else {
					request.setAttribute("mensaje", "Error: Debe introducir un topic y una descripción");
					doGet(request, response);
				}
			} break;
			
			case "eliminar":{
				
				if (    request.getParameter("articulo_id") != null 
						&& !request.getParameter("articulo_id").isEmpty()
						) {
					WikiManager manager = new WikiManager();
					manager.eliminarArticulo(request.getParameter("articulo_id"));
					request.setAttribute("mensaje", "Artículo eliminado");
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
