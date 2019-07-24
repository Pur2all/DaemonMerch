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
import model.bean.Top;
import model.dao.DBConnectionPool;
import model.dao.TopDAO;
import utils.ImageGetter;

@WebServlet("/admin/InsertTop")
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2,	// 2MB after which the file will be temporarily stored on disk
				maxFileSize=1024 * 1024 * 10,		// 10MB maximum size allowed for uploaded files
				maxRequestSize=1024 * 1024 * 50)	// 50MB overall size of all uploaded files
public class InsertTop extends HttpServlet
{
	private static final long serialVersionUID = 1238131106097045840L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Image[] image=ImageGetter.getImage(request);

		Top product=new Top();

		product.setImages(image);
		product.setDescription(request.getParameter("description"));
		product.setName(request.getParameter("name"));
		product.setPrice(Float.parseFloat(request.getParameter("price")));
		product.setRemaining(Integer.parseInt(request.getParameter("remaining")));
		product.setTag(request.getParameter("tag"));
		product.setArtistId(request.getParameter("artistId"));
		product.setCategory(model.bean.Category.valueOf(request.getParameter("category")));
		product.setPrintType(model.bean.PrintType.valueOf(request.getParameter("printType")));
		product.setSize(model.bean.Size.valueOf(request.getParameter("size")));

		TopDAO patchDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			request.setAttribute("success", (patchDAO.doSave(product)!=null ? 1 : 0));
			getServletContext().getRequestDispatcher("/DaemonMerch/AddProductForm").forward(request, response);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
