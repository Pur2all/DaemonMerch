package control.servlet.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/auth/Logout")
public class Logout extends HttpServlet
{
	private static final long serialVersionUID = 1042434985115718411L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getSession(false).removeAttribute("cart");
		request.getSession(false).removeAttribute("userInfo");
		request.getSession(false).invalidate();
		response.sendRedirect(request.getContextPath() + "/Home");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
