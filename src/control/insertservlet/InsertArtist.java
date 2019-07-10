package control.insertservlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Artist;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;

@WebServlet("/InsertArtist")
public class InsertArtist extends HttpServlet 
{
	private static final long serialVersionUID = -4942013933491000676L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameterMap().containsKey(null))
			response.sendRedirect("ErrorPage");
		else
		{
			String name=request.getParameter("name");

			Artist artist=new Artist();

			artist.setName(name);

			ArtistDAO artistDAO =new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				artistDAO.doSave(artist);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			getServletContext().getRequestDispatcher("InsertImage").forward(request, response);
		}
	}
}
