package control.servlet.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="/AuthFilter",
		urlPatterns={"/auth/*"})
public class AuthFilter implements Filter 
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		if((((HttpServletRequest) request).getSession(false))!=null)
			chain.doFilter(request, response);
		else
			((HttpServletResponse) response).sendRedirect("ErrorPage");
	}
}
