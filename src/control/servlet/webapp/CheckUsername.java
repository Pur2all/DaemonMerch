package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.UserDAO;

@WebServlet("/CheckUsername")
public class CheckUsername extends HttpServlet
{
	private static final long serialVersionUID = 7053841079424305976L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String username=request.getParameter("username");

		UserDAO userDAO=new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		System.out.println("Username in CheckUsername= " + username);
		try
		{
			boolean isAlreadyPresent=false;
			for(User u: userDAO.doRetrieveAll())
				if(u.getUsername().equalsIgnoreCase(username))
				{
					isAlreadyPresent=true;
					break;
				}
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(isAlreadyPresent));
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
