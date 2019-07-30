package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Product;
import model.dao.DBConnectionPool;
import model.dao.ProductDAO;

@WebServlet("/Products")
public class RetrieveProducts extends HttpServlet
{
	private static final long serialVersionUID = 1661985961843759472L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		int init=(Integer.parseInt(request.getParameter("page"))-1)*16, end=init+17;
		
		try
		{
			String orderString=request.getParameter("orderString");
			LinkedList<Product> products=(LinkedList<Product>) productDAO.doRetrieveAll(orderString, init, end);

			request.setAttribute("products", products);
			request.setAttribute("mainPage", "ProductsList");
			getServletContext().getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
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
