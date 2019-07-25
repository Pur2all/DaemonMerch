package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Artist;
import model.bean.Image;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;
import utils.ImageGetter;

@WebServlet("/admin/InsertArtist")
@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2,	// 2MB after which the file will be temporarily stored on disk
				maxFileSize=1024 * 1024 * 10,		// 10MB maximum size allowed for uploaded files
				maxRequestSize=1024 * 1024 * 50)	// 50MB overall size of all uploaded files
public class InsertArtist extends HttpServlet
{
	private static final long serialVersionUID = -4942013933491000676L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Image[] image=ImageGetter.getImage(request);
		Image[] images=new Image[image.length-1];

		for(int i=0; i<images.length; i++)
			images[i]=image[i+1];

		Artist artist=new Artist();

		artist.setLogo(image[0]);
		artist.setImages(images);

		ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));


		if(request.getParameter("name")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			String name=request.getParameter("name");
			artist.setName(name);

			try
			{
				if(artistDAO.doSave(artist)!=null)
					response.sendRedirect(request.getContextPath() + "/AddArtistForm");
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
