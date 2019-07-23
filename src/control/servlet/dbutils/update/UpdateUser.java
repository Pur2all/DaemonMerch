package control.servlet.dbutils.update;

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

@WebServlet("/auth/UpdateUser")
public class UpdateUser extends HttpServlet 
{
	private static final long serialVersionUID = 8328471423165150859L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		//TODO vedi come si legge sta roba
		User user=new Gson().fromJson(request.getParameter("user"), User.class);

		UserDAO userDAO=new UserDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		System.out.println(user);
		try
		{
			response.setContentType("text/plain");
			boolean updateMake=userDAO.doUpdate(user);
			response.getWriter().write(updateMake ? 1 : 0);
			if(updateMake)
				request.getSession(false).setAttribute("userInfo", user);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
