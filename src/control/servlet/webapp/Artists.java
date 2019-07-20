package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;

@WebServlet("/ArtistsProva")
public class Artists extends HttpServlet
{
	private static final long serialVersionUID = 537019613255870404L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		try
		{
			System.out.println(artistDAO.doRetrieveAll());
			request.setAttribute("artists", artistDAO.doRetrieveAll());
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}

		request.setAttribute("mainPage", "ArtistsPage");
		getServletContext().getRequestDispatcher("/Index").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
