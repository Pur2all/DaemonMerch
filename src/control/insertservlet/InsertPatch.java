package control.insertservlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patch;
import model.PatchType;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;

@WebServlet("/InsertPatch")
public class InsertPatch extends HttpServlet 
{
	private static final long serialVersionUID = 856348204473800058L;

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
			String name=request.getParameter("name"), description=request.getParameter("description"), tag=request.getParameter("tag"),
					material=request.getParameter("material"), measures=request.getParameter("measures");
			float price=Float.parseFloat(request.getParameter("price"));
			int remaining=Integer.parseInt(request.getParameter("remaining")), artistID=Integer.parseInt(request.getParameter("artistId"));
			PatchType patchType=PatchType.valueOf(request.getParameter("patchType"));
			
			Patch product=new Patch();
			
			product.setName(name);
			product.setDescription(description);
			product.setTag(tag);
			product.setPrice(price);
			product.setRemaining(remaining);
			product.setArtistId(Integer.toString(artistID));
			product.setMaterial(material);
			product.setMeasures(measures);
			product.setPatchType(patchType);
			
			PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
			try
			{
				patchDAO.doSave(product);
			} 
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			
			getServletContext().getRequestDispatcher("InsertImage").forward(request, response);
		}
	}
}
