package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Product;
import model.dao.ArtistDAO;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;
import model.dao.ProductDAO;
import model.dao.TopDAO;

@WebServlet("/Artist")
public class Artist extends HttpServlet
{
	private static final long serialVersionUID = 5745183530748350346L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{		
		ArtistDAO artistDAO=new ArtistDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		
		int init=(Integer.parseInt(request.getParameter("page"))-1)*16, end=init+16;
		
		try
		{
			request.setAttribute("artist", artistDAO.doRetrieveByKey(Integer.parseInt(request.getParameter("id"))));
			
			Collection<Product> products=productDAO.doRetrieveByArtistID(Integer.parseInt(request.getParameter("id")), init, end);
			products.addAll(patchDAO.doRetrieveByArtistID(Integer.parseInt(request.getParameter("id")), init, end));
			products.addAll(topDAO.doRetrieveByArtistID(Integer.parseInt(request.getParameter("id")), init, end));
			
			request.setAttribute("products", products);
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
