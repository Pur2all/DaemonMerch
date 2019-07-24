package control.servlet.webapp.async;

import java.sql.SQLException;

import model.dao.ProductDAO;
import model.dao.DBConnectionPool;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.bean.Cart;
import model.bean.Product;

@WebServlet("/auth/AddToCart")
public class AddToCart extends HttpServlet
{
	private static final long serialVersionUID = 5498445680335004364L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cart cart=null;

		if((cart=(Cart) request.getSession(false).getAttribute("cart"))==null)
			cart=new Cart();
		try
		{
			cart.addProduct((Product) (new ProductDAO((DBConnectionPool) getServletContext()
				.getAttribute("DriverManager"))
				.doRetrieveByKey(Integer.parseInt(request.getParameter("productId")))));
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		request.getSession(false).setAttribute("cart", cart);
		response.setContentType("plain/text");
		response.getWriter().write(1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
