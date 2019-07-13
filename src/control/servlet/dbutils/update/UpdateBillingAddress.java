package control.servlet.dbutils.update;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.BillingAddress;
import model.dao.BillingAddressDAO;
import model.dao.DBConnectionPool;

@WebServlet("/UpdateBillingAddress")
public class UpdateBillingAddress extends HttpServlet
{
	private static final long serialVersionUID = -6250726725794124158L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameterMap().containsKey(null))
			response.sendRedirect("ErrorPage");
		else
		{
			BillingAddress oldBillingAddress=new Gson().fromJson((String) request.getAttribute("oldBillingAddress"), BillingAddress.class);
			BillingAddress newBillingAddress=new Gson().fromJson((String) request.getAttribute("newBillingAddress"), BillingAddress.class);
			
			BillingAddressDAO billingAddressDAO=new BillingAddressDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));

			try
			{
				response.setContentType("text/plain");
				billingAddressDAO.doDelete(oldBillingAddress);
				response.getWriter().write(billingAddressDAO.doSave(newBillingAddress) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
