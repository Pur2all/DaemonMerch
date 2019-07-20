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

@WebFilter(filterName="/RedirectJspFilter",
			urlPatterns={"*.jsp"})
public class RedirectJspFilter implements Filter
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
	{
		((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/Home");
	}
}
