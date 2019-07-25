package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Image;
import model.bean.Patch;
import model.bean.PatchType;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;
import utils.ImageGetter;

@WebServlet("/admin/InsertPatch")
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2,	// 2MB after which the file will be temporarily stored on disk
				maxFileSize=1024 * 1024 * 10,		// 10MB maximum size allowed for uploaded files
				maxRequestSize=1024 * 1024 * 50)	// 50MB overall size of all uploaded files
public class InsertPatch extends HttpServlet
{
	private static final long serialVersionUID = 856348204473800058L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Image[] image=ImageGetter.getImage(request);

		Patch product=new Patch();

		product.setImages(image);
		product.setDescription(request.getParameter("description"));
		product.setName(request.getParameter("name"));
		product.setPrice(Float.parseFloat(request.getParameter("price")));
		product.setRemaining(Integer.parseInt(request.getParameter("remaining")));
		product.setTag(request.getParameter("tag"));
		product.setArtistId(request.getParameter("artistId").equals("") ? null : request.getParameter("artistId"));
		product.setPatchType(PatchType.valueOf(request.getParameter("patchType")));
		product.setMeasures(request.getParameter("measures"));
		product.setMaterial(request.getParameter("material"));

		PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			if(patchDAO.doSave(product)!=null)
				response.sendRedirect(request.getContextPath() + "/AddProductForm");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
