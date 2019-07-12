package control.servlet.insert;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.CreditCard;
import model.dao.CreditCardDAO;
import model.dao.DBConnectionPool;

@WebServlet("/InsertCreditCard")
public class InsertCreditCard extends HttpServlet 
{
	private static final long serialVersionUID = -8804571230137329789L;

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
			String cvv=request.getParameter("cvv"), expireDate=request.getParameter("expireDate"), number=request.getParameter("number");
			int userID=Integer.parseInt(request.getParameter("userID"));
			
			CreditCard creditCard=new CreditCard();
			
			creditCard.setCVV(cvv);
			creditCard.setExpireDate(expireDate);
			creditCard.setNumber(number);
			
			CreditCardDAO creditCardDAO=new CreditCardDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"));
			
			try
			{
				creditCardDAO.doSave(creditCard);
				creditCardDAO.saveUserCreditCardRelation(creditCard, userID);
			}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
			}
			
			response.sendRedirect("InsertBillingAddress.jsp");
		}
	}
}
