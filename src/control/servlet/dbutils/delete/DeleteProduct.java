package control.servlet.dbutils.delete;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Product;
import model.dao.DBConnectionPool;
import model.dao.ProductDAO;

@WebServlet("/servlet/DeleteProduct")
public class DeleteProduct extends HttpServlet
{
	private static final long serialVersionUID = -6548247580467322903L;

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
			Product product=new Gson().fromJson((String) request.getAttribute("product"), Product.class);

			ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(productDAO.doDelete(product) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
