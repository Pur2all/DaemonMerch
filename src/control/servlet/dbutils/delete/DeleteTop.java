package control.servlet.dbutils.delete;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Top;
import model.dao.DBConnectionPool;
import model.dao.TopDAO;

@WebServlet("/servlet/admin/DeleteTop")
public class DeleteTop extends HttpServlet
{
	private static final long serialVersionUID = 279430285158310032L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("top")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			Top top=new Gson().fromJson((String) request.getAttribute("top"), Top.class);

			TopDAO topDAO=new TopDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
				Integer.parseInt(request.getParameter("pageInit")), Integer.parseInt(request.getParameter("pageEnd")));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(topDAO.doDelete(top) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
