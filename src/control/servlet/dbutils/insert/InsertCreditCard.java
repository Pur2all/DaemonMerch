package control.servlet.dbutils.insert;

import com.google.gson.Gson;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CreditCard;
import model.bean.User;
import model.dao.CreditCardDAO;
import model.dao.DBConnectionPool;

@WebServlet("/auth/InsertCreditCard")
public class InsertCreditCard extends HttpServlet
{
	private static final long serialVersionUID = -8804571230137329789L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(request.getAttribute("creditCard")==null)
			response.sendRedirect(request.getContextPath() + "/ErrorPage");
		else
		{
			CreditCard creditCard=new Gson().fromJson((String) request.getAttribute("credtiCard"), CreditCard.class);
			int userID=Integer.parseInt(((User)request.getSession().getAttribute("userInfo")).getId());

			CreditCardDAO creditCardDAO=new CreditCardDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), userID);

			try
			{
				response.setContentType("text/plain");
				response.getWriter().write(creditCardDAO.doSave(creditCard)!=null ? 1 : 0);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
		}
	}
}
