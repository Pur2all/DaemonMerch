package utils;

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

@WebServlet("/images/*")
public class RetrieveImageServlet extends HttpServlet 
{
	private static final long serialVersionUID = -4797597259068028275L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String imageName=request.getPathInfo().substring(1); // "/foo.png" -> "foo.png".

        try
        {
        	Image image=(new ImageDAO((DBConnectionPool) getServletContext().getAttribute("DriverManager"), -1, null)).doRetrieveByKey(imageName);
            
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