package control.servlet.webapp.async;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cart;

@WebServlet("/servlet/auth/RemoveFromCart")
public class RemoveFromCart extends HttpServlet
{
	private static final long serialVersionUID = -1507530678488154117L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Cart cart=(Cart) request.getSession(false).getAttribute("cart");

		cart.removeProduct((int) request.getAttribute("index"));
		request.getSession(false).setAttribute("cart", cart);
		response.getWriter().write(1);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
