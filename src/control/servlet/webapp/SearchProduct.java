package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.DBConnectionPool;
import model.dao.PatchDAO;
import model.dao.ProductDAO;
import model.dao.TopDAO;

@WebServlet("/SearchProduct")
public class SearchProduct extends HttpServlet
{
	private static final long serialVersionUID = 861594351321285535L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String productName=request.getParameter("q"), productType=request.getParameter("productType");
		int init=(Integer.parseInt(request.getParameter("page"))-1)*16, end=init+16;

		if(productName!=null && !productName.equals("") && productType!=null && !productType.equals(""))
		{		
			try
			{				
				switch(productType)
				{
					case "patch":
						PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
						request.setAttribute("products", patchDAO.doRetrieveByName(productName, init, end));
					break;
					case "top":
						TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
						request.setAttribute("products", topDAO.doRetrieveByName(productName, init, end));
					break;
					case "other":
						ProductDAO productDAO=new ProductDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
						request.setAttribute("products", productDAO.doRetrieveByName(productName, init, end));
					break;
				}
			}
			catch (SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			request.setAttribute("mainPage", "ProductsList");
			getServletContext().getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
		}
		else
		{
			request.setAttribute("error", "Invalid query");
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
