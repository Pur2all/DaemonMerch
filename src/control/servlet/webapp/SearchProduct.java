package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DBConnectionPool;
import model.dao.ProductDAO;

@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet
{
	private static final long serialVersionUID = 861594351321285535L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String productName=request.getParameter("q");

		if(productName!=null && !productName.equals(""))
		{
			ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				request.setAttribute("products", productDAO.doRetrieveByName(productName,
					Integer.parseInt(request.getParameter("pageInit")), Integer.parseInt(request.getParameter("pageEnd"))));
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			request.setAttribute("mainPage", "ProductsList");
			getServletContext().getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
		}
		else
		{
			request.setAttribute("error", "Invalid query");
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
