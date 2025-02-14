package control.servlet.dbutils.update;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.WishlistProduct;
import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.WishlistDAO;

@WebServlet("/auth/UpdateProductInWishlist")
public class UpdateProductInWishlist extends HttpServlet
{
	private static final long serialVersionUID = -5003518934020844020L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int userId=Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId());

		WishlistProduct wishlistProduct=new Gson().fromJson(request.getParameter("wishlistProduct"), WishlistProduct.class);

		WishlistDAO wishlistDAO=new WishlistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userId);

		try
		{
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(wishlistDAO.doUpdate(wishlistProduct)));
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
