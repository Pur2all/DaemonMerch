package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Artist;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;

@WebServlet("/RetrieveArtistsAsync")
public class RetrieveArtistsAsync extends HttpServlet 
{
	private static final long serialVersionUID = 9198968180032823695L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		LinkedList<Artist> artists=null;
		
		try
		{
			artists=(LinkedList<Artist>) artistDAO.doRetrieveAll();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}

		response.setContentType("application/json");
		response.getWriter().write(new Gson().toJson(artists));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request, response);
	}
}
