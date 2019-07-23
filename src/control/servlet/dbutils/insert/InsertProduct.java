package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Artist;
import model.bean.Image;
import model.bean.Product;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;
import model.dao.ProductDAO;
import utils.ImageGetter;

@WebServlet("/admin/InsertProduct")
public class InsertProduct extends HttpServlet
{
	private static final long serialVersionUID = 7338819740934942720L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Image[] image=ImageGetter.getImage(request);

		Product product=new Product();

		product.setImages(image);
		product.setDescription(request.getParameter("description"));
		product.setName(request.getParameter("name"));
		product.setPrice(Float.parseFloat(request.getParameter("price")));
		product.setRemaining(Integer.parseInt(request.getParameter("reamining")));
		product.setTag(request.getParameter("tag"));
		product.setArtistId(request.getParameter("artistId"));

		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));


		if(request.getParameter("name")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
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
