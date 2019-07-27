package control.servlet.dbutils.update;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.CreditCard;
import model.bean.User;
import model.dao.CreditCardDAO;
import model.dao.DBConnectionPool;

@WebServlet("/auth/UpdateCreditCard")
public class UpdateCreditCard extends HttpServlet
{
	private static final long serialVersionUID = -4599610942013407431L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		CreditCard creditCard=new Gson().fromJson(request.getParameter("credtiCard"), CreditCard.class);

		CreditCardDAO creditCardDAO=new CreditCardDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
				Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId()));

		try
		{
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(creditCardDAO.doUpdate(creditCard)));
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
