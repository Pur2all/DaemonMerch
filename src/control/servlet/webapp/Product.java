package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DAO;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;
import model.dao.ProductDAO;
import model.dao.TopDAO;

@WebServlet("/Product")
public class Product extends HttpServlet
{
	private static final long serialVersionUID = 99643134982128170L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("id")==null || request.getParameter("type")==null)
			response.sendRedirect("/ErrorPage");
		
		int id=Integer.parseInt(request.getParameter("id"));
		String type=request.getParameter("type");
		DAO<?> dao=null;
		
		switch(type)
		{
			case "patch":
				dao=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			break;
			case "top":
				dao=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			break;
			case "product":
				dao=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			break;
		}
		
		try
		{
			request.setAttribute("product", dao.doRetrieveByKey(id));
		} 
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		
		request.setAttribute("mainPage", "ProductPage");
		getServletContext().getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
