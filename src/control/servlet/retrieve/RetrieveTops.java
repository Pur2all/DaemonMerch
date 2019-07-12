package control.servlet.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Top;
import model.dao.DBConnectionPool;
import model.dao.TopDAO;

@WebServlet("/RetrieveTops")
public class RetrieveTops extends HttpServlet 
{
	private static final long serialVersionUID = 5745183530748350346L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
		
		try
		{
			LinkedList<Top> tops=(LinkedList<Top>) topDAO.doRetrieveAll(null);
			
			tops.sort(new Comparator<Top>()
			{
				public int compare(Top first, Top second)
				{
					return first.getName().compareTo(second.getName());
				}
			});
			
			request.setAttribute("tops", tops);
			
			getServletContext().getRequestDispatcher("Artist").forward(request, response);
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
