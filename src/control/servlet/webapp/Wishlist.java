package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.WishlistDAO;

@WebServlet("/auth/Wishlist")
public class Wishlist extends HttpServlet
{
	private static final long serialVersionUID = 3969318835120065202L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int userId=Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId());

		WishlistDAO wishlistDAO=new WishlistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userId);

		try
		{
			request.setAttribute("wishlistProduct", wishlistDAO.doRetrieveByUserID());
			request.setAttribute("mainPage", "WishlistPage");
			getServletContext().getRequestDispatcher("/Index").forward(request, response);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
