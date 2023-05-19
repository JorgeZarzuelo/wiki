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
import wiki.manager.WikiManager;

/**
 * Servlet Filter implementation class OnlyGestorFilter
 */
@WebFilter("/OnlyGestorFilter")
public class OnlyGestorFilter extends HttpFilter implements Filter {
       

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpSession session = httpReq.getSession(false);
		if (session != null && session.getAttribute("user") != null) {
			User currentUser = (User) session.getAttribute("user");
			if (currentUser.isGestor() == true) {
				chain.doFilter(request, response);
			}	else {
				HttpServletResponse httpResp = (HttpServletResponse) response;
				httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			}		
		} else {
			HttpServletResponse httpResp = (HttpServletResponse) response;
			httpResp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
		}
		
		
	}



}
