package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.WishlistProduct;
import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.WishlistDAO;

@WebServlet("/auth/Wishlist")
public class RetrieveProductsInWishlist extends HttpServlet
{
	private static final long serialVersionUID = 4832505934016090804L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int userId=Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId());

		WishlistDAO wishlistDAO=new WishlistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userId);

		try
		{
			LinkedList<WishlistProduct> wishlistProducts=(LinkedList<WishlistProduct>) wishlistDAO.doRetrieveByUserID();

			request.setAttribute("wishlistProducts", wishlistProducts);
			request.setAttribute("mainPage", "Wishlist");

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
