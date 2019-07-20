package control.servlet.dbutils.update;

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

@WebServlet("/admin/UpdatePatch")
public class UpdatePatch extends HttpServlet
{
	private static final long serialVersionUID = 8117543433606527866L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Patch patch=new Gson().fromJson((String) request.getAttribute("patch"), Patch.class);

		PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			response.setContentType("text/plain");
			response.getWriter().write(patchDAO.doUpdate(patch) ? 1 : 0);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
