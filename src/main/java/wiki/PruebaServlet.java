package wiki;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/formula")
public class PruebaServlet extends HttpServlet{

	private static final long serialVersionUID = -7615309054596944271L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			
			out.println("<!DOCTYPE html><html><head></head><body>writer funcionando si!</body></html>");
			
			out.close();
			
		}
}
