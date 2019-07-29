package control.servlet.dbutils.retrieve;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CreditCard;
import model.bean.User;
import model.dao.CreditCardDAO;
import model.dao.DBConnectionPool;

@WebServlet("/auth/CreditCards")
public class RetrieveCreditCards extends HttpServlet
{
	private static final long serialVersionUID = 6959272245530999912L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		CreditCardDAO creditCardDAO=new CreditCardDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
				Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId()));

		try
		{
			LinkedList<CreditCard> creditCards=(LinkedList<CreditCard>) creditCardDAO.doRetrieveAll();

			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(creditCards));
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
