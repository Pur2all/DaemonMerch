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

@WebServlet("/Artist")
public class Artist extends HttpServlet
{
	private static final long serialVersionUID = 5745183530748350346L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		
		try
		{
			request.setAttribute("artist", artistDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))));
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}

		request.setAttribute("mainPage", "ArtistPage");
		getServletContext().getRequestDispatcher("/Index").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
