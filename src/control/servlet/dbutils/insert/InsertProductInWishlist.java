package control.servlet.dbutils.insert;

import com.google.gson.Gson;

import model.dao.ProductDAO;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.WishlistProduct;
import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.WishlistDAO;

@WebServlet("/auth/AddToWishlist")
public class InsertProductInWishlist extends HttpServlet
{
	private static final long serialVersionUID = 8699191442651130979L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("productId")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			try
			{
				int userId=Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId());

				WishlistProduct product=((WishlistProduct) (new ProductDAO((DBConnectionPool) getServletContext()
					.getAttribute("DriverManager"))
					.doRetrieveByKey(Integer.parseInt(request.getParameter("productId")))));

				WishlistDAO productDAO=new WishlistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userId);

				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(productDAO.doSave(product)));
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
