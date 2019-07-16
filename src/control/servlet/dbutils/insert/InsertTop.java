package control.servlet.dbutils.insert;

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

@WebServlet("/servlet/InsertTop")
public class InsertTop extends HttpServlet
{
	private static final long serialVersionUID = 1238131106097045840L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("top")==null)
			response.sendRedirect("ErrorPage");
		else
		{
			Top product=new Gson().fromJson((String) request.getAttribute("top"), Top.class);

			TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				topDAO.doSave(product);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			getServletContext().getRequestDispatcher("InsertImage").forward(request, response);
		}
	}
}
