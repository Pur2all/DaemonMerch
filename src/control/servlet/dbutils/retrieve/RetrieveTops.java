package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Top;
import model.dao.DBConnectionPool;
import model.dao.TopDAO;

@WebServlet("/Tops")
public class RetrieveTops extends HttpServlet
{
	private static final long serialVersionUID = 5745183530748350346L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			String orderString=request.getParameter("orderString");
			LinkedList<Top> tops=(LinkedList<Top>) topDAO.doRetrieveAll(orderString, Integer.parseInt(request.getParameter("pageInit")), Integer.parseInt(request.getParameter("pageEnd")));

			request.setAttribute("products", tops);
			request.setAttribute("mainPage", "ProductsList");

			getServletContext().getRequestDispatcher("/Index").forward(request, response);
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
