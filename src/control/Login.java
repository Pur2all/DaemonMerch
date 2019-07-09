package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String username=(String) request.getParameter("username"), password=(String) request.getParameter("password");

		if(username!=null && password!=null)
		{
			UserDAO userDAO=new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				if(userDAO.searchForUsernameAndPassword(username, password))
					response.sendRedirect("Home");
				else
				{
					request.setAttribute("error", Boolean.TRUE);
					RequestDispatcher requestDispatcher=getServletContext().getRequestDispatcher("/HTML/Login.jsp");
					requestDispatcher.forward(request, response);
				}
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}

}
