package wiki.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import wiki.entities.User;
import wiki.managers.WikiManager;

/**
 * Servlet Filter implementation class OnlyGestorFilter
 */
@WebFilter("/OnlyCoordinadorFilter")
public class OnlyCoordinadorFilter extends HttpFilter implements Filter {
       

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession(false);
		if (session != null && session.getAttribute("user") != null && session.getAttribute("isCoordinador") != null) {
			boolean isCoordinador = (boolean) session.getAttribute("isCoordinador");
			if (isCoordinador) {
				chain.doFilter(request, response);
			}	else {
				HttpServletResponse httpResp = (HttpServletResponse) response;
				httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}		
		} else {
			System.out.println("sesion nula");
			HttpServletResponse httpResp = (HttpServletResponse) response;
			httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		
	}



}
