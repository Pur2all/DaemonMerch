package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Patch;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;

@WebServlet("/RetrievePatches")
public class RetrievePatches extends HttpServlet
{
	private static final long serialVersionUID = 3964359746975342691L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			LinkedList<Patch> patches=(LinkedList<Patch>) patchDAO.doRetrieveAll(null, (int) request.getAttribute("pageInit"), (int) request.getAttribute("pageEnd"));

			patches.sort(new Comparator<Patch>()
			{
				public int compare(Patch first, Patch second)
				{
					return first.getName().compareTo(second.getName());
				}
			});

			request.setAttribute("patches", patches);
			request.setAttribute("mainPage", "Patches");

			getServletContext().getRequestDispatcher("/Index").forward(request, response);
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
