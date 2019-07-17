package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Artist;
import model.bean.Image;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;
import utils.ImageGetter;

@WebServlet("/servlet/admin/InsertArtist")
public class InsertArtist extends HttpServlet
{
	private static final long serialVersionUID = -4942013933491000676L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("name")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			String name=request.getParameter("name");
			Image image=ImageGetter.getImage(request);

			Artist artist=new Artist();

			artist.setName(name);
			try
			{
				artist.setLogo(image.getImage().getBytes(1, (int) image.getImage().length()));
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(artistDAO.doUpdate(artist) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
