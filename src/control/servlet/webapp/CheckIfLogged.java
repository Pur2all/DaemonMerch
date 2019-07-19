package control.servlet.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.bean.UserType;

@WebServlet("/servlet/CheckIfLogged")
public class CheckIfLogged extends HttpServlet 
{
	private static final long serialVersionUID = 2478406480045722293L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getSession(false).getAttribute("userInfo")!=null)
			if(((User) request.getSession(false).getAttribute("userInfo")).getUserType()==UserType.ADMIN)
				response.sendRedirect(request.getContextPath() + "/AdminArea");
			else
				response.sendRedirect(request.getContextPath() + "/UserProfile");
		else
		{
			request.setAttribute("error", Boolean.TRUE);
			response.sendRedirect(request.getContextPath() + "/LoginForm");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
