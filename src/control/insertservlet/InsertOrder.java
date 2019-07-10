package control.insertservlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.BillingAddress;
import model.Order;
import model.Product;
import model.State;
import model.dao.DBConnectionPool;
import model.dao.OrderDAO;

@WebServlet("/InsertOrder")
public class InsertOrder extends HttpServlet 
{
	private static final long serialVersionUID = -6431170288308520299L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		if(request.getParameterMap().containsKey(null))
			response.sendRedirect("ErrorPage");
		else
		{
			int userID=Integer.parseInt(request.getParameter("userID"));
			String date=request.getParameter("date");
			float total=Float.parseFloat(request.getParameter("total"));
			State state=State.valueOf(request.getParameter("state"));
			BillingAddress billingAddress=(BillingAddress) request.getAttribute("billingAddress");
			ArrayList<Product> products=(ArrayList<Product>) request.getAttribute("products");
			
			Order order=new Order();
			
			order.setDate(date);
			order.setTotal(total);
			order.setState(state);
			order.setBillingAddress(billingAddress);
			for(Product p: products)
				order.addProducts(p);
			
			OrderDAO orderDAO =new OrderDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userID);
			
			try
			{
				orderDAO.doSave(order);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			
			response.sendRedirect("Orders.jsp");
		}
	}
}
