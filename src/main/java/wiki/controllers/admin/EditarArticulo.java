package wiki.controllers.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.entities.Articulo;
import wiki.entities.User;
import wiki.managers.WikiManager;

/**
 * Servlet implementation class EditarArticulo
 */
@WebServlet(
		name="EditarArticulo",
		value= {"/editar_articulo"}
		)
public class EditarArticulo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarArticulo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if(    request.getParameter("operacion") != null 
				&& !request.getParameter("operacion").isEmpty()
				&& request.getParameter("articulo_id") != null 
				&& !request.getParameter("articulo_id").isEmpty())
		{
			WikiManager manager = new WikiManager();
			Articulo currentArticulo = manager.getArticuloByID(request.getParameter("articulo_id"));
			request.setAttribute("articulo", currentArticulo);
					
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/admin/editar_articulo.jsp");	
			rd.forward(request, response);
		}else {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/errors/401.jsp");	
			rd.forward(request, response);
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
			
			case "editar":{
				if (    request.getParameter("articulo_id") != null 
						&& !request.getParameter("articulo_id").isEmpty()
						&& request.getParameter("titulo") != null 
						&& !request.getParameter("titulo").isEmpty()
						&& request.getParameter("contenido") != null 
						&& !request.getParameter("contenido").isEmpty()
						) {
					WikiManager manager = new WikiManager();
					manager.editarArticulo(request.getParameter("articulo_id"), request.getParameter("titulo"), request.getParameter("contenido"));
					request.setAttribute("mensaje", "Artículo editado");	
					
					doGet(request, response);
				} else {
					request.setAttribute("mensaje", "Error: Debe introducir un título y descripción");
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
