package control.servlet.dbutils.retrieve;

import java.sql.SQLException;

import model.dao.DBConnectionPool;
import model.dao.OrderDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/admin/AllOrdersHistory")
public class RetrieveAllOrders extends HttpServlet
{
	private static final long serialVersionUID = 1042434985115718411L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	    OrderDAO orderDAO=new OrderDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), -1);
	    int init=(Integer.parseInt(request.getParameter("page"))-1)*16, end=init+17;
	    
		try
	    {
	      request.setAttribute("orders", orderDAO.doRetrieveForAllUsers(init, end));
	    }
	    catch(SQLException sqlException)
	    {
	      sqlException.printStackTrace();
	    }
	
	    request.setAttribute("mainPage", "OrdersHistory");
	    request.getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
