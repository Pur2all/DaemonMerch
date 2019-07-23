package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Image;
import model.bean.Product;
import model.dao.DBConnectionPool;
import model.dao.ProductDAO;
import utils.ImageGetter;

@WebServlet("/admin/InsertProduct")
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2,	// 2MB after which the file will be temporarily stored on disk
				maxFileSize=1024 * 1024 * 10,		// 10MB maximum size allowed for uploaded files
				maxRequestSize=1024 * 1024 * 50)	// 50MB overall size of all uploaded files
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
		product.setRemaining(1);
		//product.setRemaining(Integer.parseInt(request.getParameter("remaining")));
		product.setTag(request.getParameter("tag"));
		product.setArtistId(request.getParameter("artistId"));

		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));


		try
		{
			request.setAttribute("success", (productDAO.doSave(product) ? 1 : 0));
			request.getRequestDispatcher("/AddArtistForm").forward(request, response);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
