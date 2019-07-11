package control.insertservlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.BillingAddress;
import model.dao.BillingAddressDAO;
import model.dao.DBConnectionPool;


@WebServlet("/InsertBillingAddress")
public class InsertBillingAddress extends HttpServlet 
{
	private static final long serialVersionUID = -3229112474089218224L;

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
			String state=request.getParameter("state"), street=request.getParameter("street"), city=request.getParameter("city"), 
					district=request.getParameter("district"), houseNumber=request.getParameter("houseNumber");
			
			BillingAddress billingAddress=new BillingAddress();
			
			billingAddress.setState(state);
			billingAddress.setStreet(street);
			billingAddress.setCity(city);
			billingAddress.setDistrict(district);
			billingAddress.setHouseNumber(houseNumber);
			
			BillingAddressDAO billingAddressDAO=new BillingAddressDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
			try
			{
				billingAddressDAO.doSave(billingAddress);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			
			response.sendRedirect("InsertBillingAddress.jsp");
		}
	}
}
