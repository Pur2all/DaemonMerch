package control.insertservlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.WishlistProduct;
import model.dao.DBConnectionPool;
import model.dao.WishlistDAO;

@WebServlet("/InsertProductInWishlist")
public class InsertProductInWishlist extends HttpServlet 
{
	private static final long serialVersionUID = 8699191442651130979L;

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
			String dateOfAddition=request.getParameter("dateOfAddition");
			int quantity=Integer.parseInt(request.getParameter("quantity"));
			
			WishlistProduct product=new WishlistProduct();
			
			product.setDateOfAddition(dateOfAddition);
			product.setQuantity(quantity);
			
			WishlistDAO productDAO=new WishlistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
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
