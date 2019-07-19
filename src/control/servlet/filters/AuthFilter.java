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
		urlPatterns={"/servlet/auth/*"})
public class AuthFilter implements Filter 
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		HttpServletRequest servletRequest=(HttpServletRequest) request;
		HttpServletResponse servletResponse=(HttpServletResponse) response;
		System.out.println("Auth filter");
		if(servletRequest.getSession(false)!=null && servletRequest.getSession(false).getAttribute("userInfo")!=null)
			chain.doFilter(request, response);
		else
			servletResponse.sendRedirect(servletRequest.getContextPath() + "/ErrorPage");
	}
}
