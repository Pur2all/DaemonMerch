package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.WishlistProduct;
import model.dao.DBConnectionPool;
import model.dao.WishlistDAO;

@WebServlet("/servlet/auth/InsertProductInWishlist")
public class InsertProductInWishlist extends HttpServlet
{
	private static final long serialVersionUID = 8699191442651130979L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("wishlistProduct")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			WishlistProduct product=new Gson().fromJson((String) request.getAttribute("wishlistProduct"), WishlistProduct.class);

			WishlistDAO productDAO=new WishlistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(productDAO.doSave(product) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
