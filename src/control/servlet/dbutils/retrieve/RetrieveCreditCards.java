package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CreditCard;
import model.dao.CreditCardDAO;
import model.dao.DBConnectionPool;

@WebServlet("/RetrieveCreditCards")
public class RetrieveCreditCards extends HttpServlet
{
	private static final long serialVersionUID = 6959272245530999912L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		CreditCardDAO creditCardDAO=new CreditCardDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), Integer.parseInt(request.getParameter("userID")));

		try
		{
			LinkedList<CreditCard> creditCards=(LinkedList<CreditCard>) creditCardDAO.doRetrieveAll(null);

			request.setAttribute("creditCards", creditCards);

			getServletContext().getRequestDispatcher("CreditCards").forward(request, response);
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
