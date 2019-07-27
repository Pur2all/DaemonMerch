package control.servlet.dbutils.update;

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

@WebServlet("/admin/UpdateProduct")
public class UpdateProduct extends HttpServlet
{
	private static final long serialVersionUID = 7917186766059734018L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Product product=new Gson().fromJson(request.getParameter("product"), Product.class);

		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(productDAO.doUpdate(product)));
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
