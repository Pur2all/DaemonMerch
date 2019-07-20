package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import model.bean.Image;
import model.dao.DBConnectionPool;
import model.dao.ImageDAO;
import model.dao.TypeOfImage;
import utils.ImageGetter;

@WebServlet("/auth/InsertImage")
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2,	// 2MB after which the file will be temporarily stored on disk
				 maxFileSize=1024 * 1024 * 10,		// 10MB maximum size allowed for uploaded files
				 maxRequestSize=1024 * 1024 * 50)	// 50MB overall size of all uploaded files
public class InsertImage extends HttpServlet
{
	private static final long serialVersionUID = -4011837396284189680L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.setContentType("text/plain");

		response.getWriter().write("Error: GET method is used but POST method is required");
		response.getWriter().close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("id")==null || request.getParameter("typeOfImage")==null)
			response.sendRedirect("ErrorPage");
		else
		{
			Image image=ImageGetter.getImage(request);

			ImageDAO imageDAO=new ImageDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
					Integer.parseInt(request.getParameter("id")), TypeOfImage.valueOf(request.getParameter("typeOfImage")));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(imageDAO.doSave(image) ? 1 : 0);
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
