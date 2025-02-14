package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.UserDAO;

@WebServlet("/Login")
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 2794033284698836496L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username=request.getParameter("username"), password=request.getParameter("password");

		if(username!=null && password!=null)
		{
			UserDAO userDAO=new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			User loggedUser=null;
			try
			{
				if((loggedUser=userDAO.searchForUsernameAndPassword(username, password))!=null)
				{
					HttpSession session=request.getSession(true);
					session.setAttribute("userInfo", loggedUser);
					response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/Home"));
				}
				else
				{
					request.setAttribute("loginError", Boolean.TRUE);
					getServletContext().getRequestDispatcher(("/LoginForm")).forward(request, response);
				}
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
		else
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
	}
}
