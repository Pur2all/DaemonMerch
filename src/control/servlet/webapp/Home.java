package control.servlet.webapp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Home")
public class Home extends HttpServlet
{
	private static final long serialVersionUID = -5699762808641465273L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.setAttribute("mainPage", "Main");
		getServletContext().getRequestDispatcher(response.encodeURL("/Index")).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
