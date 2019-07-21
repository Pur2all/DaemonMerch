package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.bean.UserType;
import model.dao.DBConnectionPool;
import model.dao.UserDAO;

@WebServlet("/Registration")
public class Registration extends HttpServlet
{
	private static final long serialVersionUID = -3640960812226803240L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("name")==null || request.getParameter("surname")==null || request.getParameter("birthday")==null ||
			request.getParameter("username")==null || request.getParameter("password")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			User user=new User();
			user.setBirthday((String) request.getParameter("birthday"));
			user.setName((String) request.getParameter("name"));
			user.setSurname((String) request.getParameter("surname"));
			user.setUsername((String) request.getParameter("username"));
			user.setUserType(UserType.REGISTERED_USER);
			user.setPassword((String) request.getParameter("password"));

			UserDAO userDAO=new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				if(userDAO.doSave(user))
				{
					request.setAttribute("username", user.getUsername());
					request.setAttribute("password", user.getPassword());
					getServletContext().getRequestDispatcher("/Login").forward(request, response);
				}
				else
				{
					response.sendRedirect(request.getContextPath() + "/ErrorPage");
				}
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
