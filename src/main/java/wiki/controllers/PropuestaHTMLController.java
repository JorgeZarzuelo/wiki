package wiki.controllers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import wiki.entities.Articulo;
import wiki.entities.User;
import wiki.managers.WikiManager;
import wiki.utils.Tools;

/**
 * Servlet implementation class PropuestaHTMLController
 */

@WebServlet(
		name="PropuestaHTMLController",
				value= {"/html"}
		)
public class PropuestaHTMLController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PropuestaHTMLController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (    request.getParameter("articulo_id") != null 
				&& !request.getParameter("articulo_id").isEmpty() 
				) {
			
			WikiManager manager = new WikiManager();
			Articulo currentArticulo = manager.getArticuloByID(request.getParameter("articulo_id"));
			String html = currentArticulo.getContenido();
			
			response.setContentType("text/html");
			response.setHeader("Content-disposition", "attachment; filename=contenido.html");
			
			ServletOutputStream out = response.getOutputStream();
			
			InputStream inHtml = new ByteArrayInputStream(html.getBytes());
			
			final int SIZE = 1048;
			byte[] buffer = new byte[SIZE];
			
			int bytesLeidos;
			while ((bytesLeidos = inHtml.read(buffer)) > 0) {
                out.write(buffer, 0, bytesLeidos);
            }
			
			
		}else {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		     String html = null;
		     String articulo_id = null;
			
			if(ServletFileUpload.isMultipartContent(request)) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
			    factory.setSizeThreshold(1024 * 1024);
			    factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			    
			    ServletFileUpload upload = new ServletFileUpload(factory);
			    upload.setFileSizeMax(1024 * 1024 * 5);
			    upload.setSizeMax(1024 * 1024 * 5 * 5);
			    
			    
			    
			    try {
					List<FileItem> formItems = upload.parseRequest(request);
					if (formItems != null && formItems.size() > 0) {
						//recorremos los items enviados en el formulario
						for (FileItem item : formItems) {
							//no es un campo de formulario corriente, es un archivo
							if (!item.isFormField()) {
								   try {
										
										InputStream inHtml = item.getInputStream();
										html = new BufferedReader(
												new InputStreamReader(inHtml, StandardCharsets.UTF_8))
										        .lines()
										        .collect(Collectors.joining("\n"));
										
									} catch (Exception e) {
										
										request.setAttribute("mensaje", "Problema al leer el archivo");
										e.printStackTrace();
									}					               
							}
							//es un campo corriente
							if (item.isFormField() && item.getFieldName().equals("articulo_id")) {
									articulo_id = item.getString();
							}
						}
					}else {
						request.setAttribute("mensaje", "No se ha subido ningun archivo");
					}
				} catch (FileUploadException e) {
					request.setAttribute("mensaje", "fallo al subir el archivo");
					e.printStackTrace();
				}
			}
						
			
			HttpSession session = request.getSession(false);
			if (session != null && session.getAttribute("user") != null) {
				User currentUser = (User) session.getAttribute("user");
				WikiManager manager = new WikiManager();				
				manager.creaRevision(currentUser, articulo_id, html);				
			}
			request.setAttribute("mensaje", "Petición de modificación correctamente registrada");
			RequestDispatcher rd = request.getRequestDispatcher("/mis_propuestas");			
			rd.forward(request, response);
			
			
				
	}

}
