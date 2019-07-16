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

@WebServlet("/servlet/InsertOrder")
public class InsertOrder extends HttpServlet
{
	private static final long serialVersionUID = -6431170288308520299L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("order")==null || ((User) request.getSession().getAttribute("userInfo")).getId()==null)
			response.sendRedirect("ErrorPage");
		else
		{
			int userID=Integer.parseInt(((User) request.getSession().getAttribute("userInfo")).getId());

			Order order=new Gson().fromJson((String) request.getAttribute("order"), Order.class);

			OrderDAO orderDAO =new OrderDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userID);

			try
			{
				orderDAO.doSave(order);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}

			response.sendRedirect("Orders");
		}
	}
}
