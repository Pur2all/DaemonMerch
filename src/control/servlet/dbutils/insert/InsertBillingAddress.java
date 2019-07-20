package control.servlet.dbutils.insert;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.BillingAddress;
import model.bean.User;
import model.dao.BillingAddressDAO;
import model.dao.DBConnectionPool;


@WebServlet("/auth/InsertBillingAddress")
public class InsertBillingAddress extends HttpServlet
{
	private static final long serialVersionUID = -3229112474089218224L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("billingAddress")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			BillingAddress billingAddress=new Gson().fromJson((String) request.getAttribute("billingAddress"), BillingAddress.class);

			BillingAddressDAO billingAddressDAO=new BillingAddressDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
					Integer.parseInt(((User) request.getSession().getAttribute("userInfo")).getId()));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(billingAddressDAO.doSave(billingAddress) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
