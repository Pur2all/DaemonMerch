package control.servlet.async;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CheckIfLogged")
public class CheckIfLogged extends HttpServlet 
{
	private static final long serialVersionUID = 2478406480045722293L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getSession().getAttribute("userInfo")!=null)
			response.sendRedirect("UserProfile");
		else
		{
			request.setAttribute("error", Boolean.TRUE);
			RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/HTML/Login.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}

}
