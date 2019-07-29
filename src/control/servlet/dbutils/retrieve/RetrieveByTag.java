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
import model.dao.PatchDAO;
import model.dao.ProductDAO;
import model.dao.TopDAO;

@WebServlet("/RetrieveByTag")
public class RetrieveByTag extends HttpServlet 
{
	private static final long serialVersionUID = 6272576421525703570L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameter("tag")==null)
		{
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		}
		else
		{
			ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
			String tag=request.getParameter("tag");
			
			try
			{
				LinkedList<Product> products=(LinkedList<Product>) productDAO.doRetrieveByTag(tag);
				products.addAll(patchDAO.doRetrieveByTag(tag));
				products.addAll(topDAO.doRetrieveByTag(tag));
				
				request.setAttribute("products", products);
				request.setAttribute("mainPage", "ProductsList");
				getServletContext().getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
			} 
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
