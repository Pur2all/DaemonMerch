package control.servlet.dbutils.delete;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Artist;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;

@WebServlet("/servlet/DeleteArtist")
public class DeleteArtist extends HttpServlet
{
	private static final long serialVersionUID = -2768257519745818809L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("artist")==null)
			response.sendRedirect("ErrorPage");
		else
		{
			Artist artist=new Gson().fromJson((String) request.getAttribute("artist"), Artist.class);

			ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(artistDAO.doDelete(artist) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
