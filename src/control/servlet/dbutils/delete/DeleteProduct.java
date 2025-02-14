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

@WebServlet("/admin/DeleteProduct")
public class DeleteProduct extends HttpServlet
{
	private static final long serialVersionUID = -6548247580467322903L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("product")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			Product product=new Gson().fromJson( request.getParameter("product"), Product.class);

			ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(productDAO.doDelete(product)));
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
