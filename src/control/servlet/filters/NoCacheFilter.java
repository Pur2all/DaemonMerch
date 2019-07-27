package control.servlet.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName="/NoCacheFilter",
			urlPatterns={"/*"})
public class NoCacheFilter implements Filter 
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		((HttpServletResponse) response).setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		((HttpServletResponse) response).setDateHeader("Expires", 0);
		((HttpServletResponse) response).setHeader("Pragma", "no-cache");
		chain.doFilter(request, response);
	}
}
