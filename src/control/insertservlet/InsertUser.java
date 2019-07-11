package control.insertservlet;

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

@WebServlet("/InsertUser")
public class InsertUser extends HttpServlet 
{
	private static final long serialVersionUID = -3640960812226803240L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameterMap().containsKey(null))
			response.sendRedirect("ErrorPage");
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
			
			UserDAO userDAO =new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
			try
			{
				userDAO.doSave(user);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			
			response.sendRedirect("Home.jsp");
		}
	}
}
