package control.servlet.dbutils.insert;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Order;
import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.OrderDAO;

@WebServlet("/auth/InsertOrder")
public class InsertOrder extends HttpServlet
{
	private static final long serialVersionUID = -6431170288308520299L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getParameter("order")==null || ((User) request.getSession(false).getAttribute("userInfo")).getId()==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			int userID=Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId());

			Order order=new Gson().fromJson(request.getParameter("order"), Order.class);

			OrderDAO orderDAO =new OrderDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userID);

			try
			{
				orderDAO.doSave(order);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			getServletContext().getRequestDispatcher(response.encodeURL("auth/RetrieveOrders"));
		}
	}
}
