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
import model.dao.ProductDAO;

@WebServlet("/Artist")
public class Artist extends HttpServlet
{
	private static final long serialVersionUID = 5745183530748350346L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		try
		{
			request.setAttribute("artist", artistDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))));
			request.setAttribute("products", productDAO.doRetrieveByArtistID(Integer.parseInt(request.getParameter("id")), 
					Integer.parseInt(request.getParameter("pageInit")), Integer.parseInt(request.getParameter("pageEnd"))));
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}

		request.setAttribute("mainPage", "ArtistPage");
		getServletContext().getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
