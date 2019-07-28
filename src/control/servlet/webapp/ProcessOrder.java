package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Product;
import model.dao.DBConnectionPool;
import model.dao.ProductDAO;

@WebServlet("/auth/ProcessOrder")
public class ProcessOrder extends HttpServlet 
{
	private static final long serialVersionUID = 5310599144587464450L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		
		model.bean.Cart cart=(model.bean.Cart) request.getSession(false).getAttribute("cart");
		int sizeOfCart=cart.getNumberOfProduct();
		
		for(int i=0; i<sizeOfCart; i++)
		{
			Product product=cart.getProduct(i);
			product.setRemaining(product.getRemaining()-1);
			try
			{
				productDAO.doUpdate(product);
			} 
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
		request.setAttribute("mainPage", "OrderProcessed");
		getServletContext().getRequestDispatcher(response.encodeURL(request.getContextPath() + "/Index")).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
