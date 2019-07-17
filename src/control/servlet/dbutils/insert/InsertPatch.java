package control.servlet.dbutils.insert;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Patch;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;

@WebServlet("/servlet/admin/InsertPatch")
public class InsertPatch extends HttpServlet
{
	private static final long serialVersionUID = 856348204473800058L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("patch")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			Patch product=new Gson().fromJson((String) request.getAttribute("patch"), Patch.class);

			PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(patchDAO.doUpdate(product) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			getServletContext().getRequestDispatcher("servlet/auth/InsertImage").forward(request, response);
		}
	}
}
