package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.UserDAO;

@WebServlet("/servlet/Registration")
public class Registration extends HttpServlet
{
	private static final long serialVersionUID = -3640960812226803240L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameterMap().containsKey(null))
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			String name=request.getParameter("name"), surname=request.getParameter("surname"), username=request.getParameter("username"),
					password=request.getParameter("password"), birthday=request.getParameter("birthday");

			User user=new User();

			user.setName(name);
			user.setSurname(surname);
			user.setUsername(username);
			user.setPassword(password);
			user.setBirthday(birthday);

			UserDAO userDAO=new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
				Integer.parseInt(request.getParameter("pageInit")), Integer.parseInt(request.getParameter("pageEnd")));

			try
			{
				userDAO.doSave(user);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			getServletContext().getRequestDispatcher("servlet/Login").forward(request, response);
		}
	}
}
