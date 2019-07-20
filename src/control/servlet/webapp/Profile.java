package control.servlet.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.bean.UserType;

@WebServlet("/Profile")
public class Profile extends HttpServlet
{
	private static final long serialVersionUID = 2478406480045722293L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getSession(false).getAttribute("userInfo")!=null)
			if(((User) request.getSession(false).getAttribute("userInfo")).getUserType()==UserType.ADMIN)
				response.sendRedirect(request.getContextPath() + "/AdminArea");
			else
				{
					request.setAttribute("mainPage", "UserProfile");
					getServletContext().getRequestDispatcher("/Home").forward(request, response);
				}
		else
		{
			request.setAttribute("profileError", Boolean.TRUE);
			getServletContext().getRequestDispatcher("/LoginForm").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
