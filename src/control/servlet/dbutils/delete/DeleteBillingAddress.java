package control.servlet.dbutils.delete;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.BillingAddress;
import model.bean.User;
import model.dao.BillingAddressDAO;
import model.dao.DBConnectionPool;

@WebServlet("/servlet/DeleteBillingAddress")
public class DeleteBillingAddress extends HttpServlet
{
	private static final long serialVersionUID = -7209622442712731144L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("billingAddress")==null)
			response.sendRedirect("ErrorPage");
		else
		{
			BillingAddress billingAddress=new Gson().fromJson((String) request.getAttribute("billingAddress"), BillingAddress.class);

			BillingAddressDAO billingAddressDAO =new BillingAddressDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
					Integer.parseInt(((User) request.getSession().getAttribute("userInfo")).getId()));

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(billingAddressDAO.doDelete(billingAddress) ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
