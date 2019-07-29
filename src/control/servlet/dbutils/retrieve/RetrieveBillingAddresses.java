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

import model.bean.BillingAddress;
import model.bean.User;
import model.dao.BillingAddressDAO;
import model.dao.DBConnectionPool;

@WebServlet("/auth/BillingAddresses")
public class RetrieveBillingAddresses extends HttpServlet
{
	private static final long serialVersionUID = 7701777277576385762L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		BillingAddressDAO billingAddressDAO=new BillingAddressDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
				Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId()));

		try
		{
			LinkedList<BillingAddress> billingAddresses=(LinkedList<BillingAddress>) billingAddressDAO.doRetrieveAll();

			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(billingAddresses));
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
