package control.servlet.dbutils.update;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Top;
import model.dao.DBConnectionPool;
import model.dao.TopDAO;

@WebServlet("/servlet/admin/UpdateTop")
public class UpdateTop extends HttpServlet
{
	private static final long serialVersionUID = 7835836504205280483L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Top top=new Gson().fromJson((String) request.getAttribute("top"), Top.class);

		TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			response.setContentType("text/plain");
			response.getWriter().write(topDAO.doUpdate(top) ? 1 : 0);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
