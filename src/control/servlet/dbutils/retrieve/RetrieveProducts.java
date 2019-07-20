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

@WebServlet("/RetrieveProducts")
public class RetrieveProducts extends HttpServlet
{
	private static final long serialVersionUID = 1661985961843759472L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			String orderString=request.getParameter("orderString");
			LinkedList<Product> products=(LinkedList<Product>) productDAO.doRetrieveAll(orderString, (int) request.getAttribute("pageInit"), (int) request.getAttribute("pageEnd"));

			request.setAttribute("products", products);
			if(request.getAttribute("originPage")==null || !request.getAttribute("originPage").equals("Home"))
			{
				request.setAttribute("mainPage", "Products");
				getServletContext().getRequestDispatcher("/Index").forward(request, response);
			}
			else
				request.removeAttribute("originPage");
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
