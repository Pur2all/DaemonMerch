package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Patch;
import model.dao.DBConnectionPool;
import model.dao.PatchDAO;

@WebServlet("/Patches")
public class RetrievePatches extends HttpServlet
{
	private static final long serialVersionUID = 3964359746975342691L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PatchDAO patchDAO=new PatchDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

		try
		{
			String orderString=request.getParameter("orderString");
			LinkedList<Patch> patches=(LinkedList<Patch>) patchDAO.doRetrieveAll(orderString, (int) request.getAttribute("pageInit"), (int) request.getAttribute("pageEnd"));

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
