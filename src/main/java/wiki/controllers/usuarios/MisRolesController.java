package wiki.controllers.usuarios;

import java.io.IOException;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wiki.VO.RolVO;
import wiki.entities.Rol.Tipo;
import wiki.entities.User;
import wiki.managers.WikiManager;

@WebServlet(
		name="MisRolesController",
		value= {"/mis_roles"}
		)
public class MisRolesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MisRolesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WikiManager manager = new WikiManager();	
		request.setAttribute("wikis", manager.getWikisList());
		request.setAttribute("articulos", manager.getArticulosList());
		ArrayList<RolVO> roles = manager.getRolesVO( (User) request.getSession().getAttribute("user"));
		request.setAttribute("roles", roles);
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/vistas/mis_roles.jsp");	
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
			User currentUser = (User) request.getSession().getAttribute("user");
			switch(operacion) {
			    
				// añadir rol wiki
				case "addRoleWiki":{
					if (    
							request.getParameter("rol")  != null 
							&& !request.getParameter("rol").isEmpty()
							&& request.getParameter("wiki_id")  != null 
							&& !request.getParameter("wiki_id").isEmpty()){
						WikiManager manager = new WikiManager();
						boolean solicitado = manager.solicitarRol(Tipo.valueOf(request.getParameter("rol")) , "wiki", Integer.parseInt(request.getParameter("wiki_id")) , currentUser.getId());
						if (solicitado) {
							request.setAttribute("mensaje", "Solicitado rol sobre wiki");
						}else {
							request.setAttribute("mensaje", "Ya tiene este rol o ya esta solicitado");
						}
						
						doGet(request, response);
					}
				} break;
				// añadir rol articulo
				case "addRoleArticulo":{
					if (    request.getParameter("rol")  != null 
							&& !request.getParameter("rol").isEmpty()
							&& request.getParameter("articulo_id")  != null 
							&& !request.getParameter("articulo_id").isEmpty()){
						WikiManager manager = new WikiManager();
						boolean solicitado = manager.solicitarRol(Tipo.valueOf(request.getParameter("rol")) , "articulo", Integer.parseInt(request.getParameter("articulo_id")), currentUser.getId());
						if (solicitado) {
							request.setAttribute("mensaje", "Solicitado rol sobre artículo");
						}else {
							request.setAttribute("mensaje", "Ya tiene este rol o ya esta solicitado");
						}
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
