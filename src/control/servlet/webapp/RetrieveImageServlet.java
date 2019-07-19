package control.servlet.webapp;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Image;
import model.dao.DBConnectionPool;
import model.dao.ImageDAO;
import model.dao.TypeOfImage;

@WebServlet("/Images/*")
public class RetrieveImageServlet extends HttpServlet
{
	private static final long serialVersionUID = -4797597259068028275L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	System.out.println(request.getPathInfo());
        String imageName=request.getPathInfo().substring(1).replace("%20", " "); // "/foo.png" -> "foo.png".

        try
        {
        	Image image=null;
        	
        	if(request.getHeader("Referer").equalsIgnoreCase("http://localhost:8080/DaemonMerch/Artists"))
        		image=(new ImageDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), -1, null)).doRetrieveByKeyInArtist(imageName);
        	else
        		image=(new ImageDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), Integer.parseInt(request.getParameter("id")),
        				request.getHeader("Referer").contains("http://localhost:8080/DaemonMerch/Product") ? TypeOfImage.PRODUCT : TypeOfImage.ARTIST))
        				.doRetrieveByKey(imageName);
        			
            image.setImageName(imageName);

            if(image.getImage().length==0)
            	response.sendError(HttpServletResponse.SC_NOT_FOUND);
            else
            {
            	response.setContentType(getServletContext().getMimeType(imageName));
                response.setContentLength((int) image.getImage().length);
                response.getOutputStream().write(image.getImage());
            }
        }
        catch(SQLException sqlException)
        {
        	sqlException.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}
}
