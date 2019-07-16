package control.servlet.dbutils.retrieve;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Order;
import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.OrderDAO;

@WebServlet("/servlet/auth/RetrieveOrders")
public class RetrieveOrders extends HttpServlet
{
	private static final long serialVersionUID = 5046150917986327220L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		OrderDAO orderDAO=new OrderDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
				Integer.parseInt(((User) request.getSession().getAttribute("userInfo")).getId()));

		try
		{
			LinkedList<Order> order=(LinkedList<Order>) orderDAO.doRetrieveAll(null);

			request.setAttribute("orders", order);
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
