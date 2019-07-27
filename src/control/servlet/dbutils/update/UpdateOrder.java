package control.servlet.dbutils.update;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.bean.Order;
import model.bean.User;
import model.dao.DBConnectionPool;
import model.dao.OrderDAO;

@WebServlet("/admin/UpdateOrder")
public class UpdateOrder extends HttpServlet
{
	private static final long serialVersionUID = 8260432438802305074L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Order order=new Gson().fromJson(request.getParameter("order"), Order.class);

		OrderDAO orderDAO=new OrderDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"),
				Integer.parseInt(((User) request.getSession(false).getAttribute("userInfo")).getId()));

		try
		{
			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(orderDAO.doUpdate(order)));
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
}
