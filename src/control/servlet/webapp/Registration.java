package control.servlet.webapp;

import com.google.gson.Gson;

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
		if(request.getAttribute("newUser")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			User user=new Gson().fromJson((String) request.getAttribute("newUser"), User.class);

			UserDAO userDAO=new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				if(userDAO.doSave(user))
				{
					request.setAttribute("username", user.getUsername());
					request.setAttribute("password", user.getPassword());
					getServletContext().getRequestDispatcher("servlet/Login").forward(request, response);
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
