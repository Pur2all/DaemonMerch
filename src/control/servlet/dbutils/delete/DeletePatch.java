package control.servlet.dbutils.delete;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Patch;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;

@WebServlet("/admin/DeletePatch")
public class DeletePatch extends HttpServlet
{
	private static final long serialVersionUID = -2285916888690744812L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("patch")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			Patch patch=new Gson().fromJson(request.getParameter("patch"), Patch.class);

			PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(patchDAO.doDelete(patch)));
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
