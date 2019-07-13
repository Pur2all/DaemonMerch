package control.servlet.dbutils.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Category;
import model.bean.PrintType;
import model.bean.Size;
import model.bean.Top;
import model.dao.DBConnectionPool;
import model.dao.TopDAO;

@WebServlet("/InsertTop")
public class InsertTop extends HttpServlet 
{
	private static final long serialVersionUID = 1238131106097045840L;

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
			String name=request.getParameter("name"), description=request.getParameter("description"), tag=request.getParameter("tag");
			float price=Float.parseFloat(request.getParameter("price"));
			int remaining=Integer.parseInt(request.getParameter("remaining")), artistID=Integer.parseInt(request.getParameter("artistId"));
			Size size=Size.valueOf(request.getParameter("size"));
			Category category=Category.valueOf(request.getParameter("category"));
			PrintType printType=PrintType.valueOf(request.getParameter("printType"));
			
			Top product=new Top();
			
			product.setName(name);
			product.setDescription(description);
			product.setTag(tag);
			product.setPrice(price);
			product.setRemaining(remaining);
			product.setArtistId(Integer.toString(artistID));
			product.setSize(size);
			product.setCategory(category);
			product.setPrintType(printType);
			
			TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
			try
			{
				topDAO.doSave(product);
			} 
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			
			getServletContext().getRequestDispatcher("InsertImage").forward(request, response);
		}
	}
}
