package control.insertservlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Product;
import model.dao.DBConnectionPool;
import model.dao.ProductDAO;

@WebServlet("/InsertProduct")
public class InsertProduct extends HttpServlet
{
	private static final long serialVersionUID = 7338819740934942720L;

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
			String name=request.getParameter("name"), description=request.getParameter("description"), tag=request.getParameter("tag");
			float price=Float.parseFloat(request.getParameter("price"));
			int remaining=Integer.parseInt(request.getParameter("remaining")), artistID=Integer.parseInt(request.getParameter("artistId"));
			
			Product product=new Product();
			
			product.setName(name);
			product.setDescription(description);
			product.setTag(tag);
			product.setPrice(price);
			product.setRemaining(remaining);
			product.setArtistId(Integer.toString(artistID));
			
			ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
			try
			{
				productDAO.doSave(product);
			} 
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			
			getServletContext().getRequestDispatcher("InsertImage").forward(request, response);
		}
	}
}
