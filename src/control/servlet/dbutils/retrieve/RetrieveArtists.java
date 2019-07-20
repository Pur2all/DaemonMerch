package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Artist;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;

@WebServlet("/Artists")
public class RetrieveArtists extends HttpServlet
{
	private static final long serialVersionUID = 5020282298100482632L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			LinkedList<Artist> artists=(LinkedList<Artist>) artistDAO.doRetrieveAll(null, (int) request.getAttribute("pageInit"), (int) request.getAttribute("pageEnd"));

			artists.sort(new Comparator<Artist>()
			{
				public int compare(Artist first, Artist second)
				{
					return first.getName().compareTo(second.getName());
				}
			});

			request.setAttribute("artists", artists);
			request.setAttribute("mainPage", "Artists");

			getServletContext().getRequestDispatcher("/Index").forward(request, response);
		}
		catch (SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
