package control.insertservlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import org.apache.tomcat.util.http.fileupload.MultipartStream;

@WebServlet(name = "/InsertImage", 
			urlPatterns = "/ImageUpload" )
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB after which the file will be 
													  // temporarily stored on disk
				 maxFileSize = 1024 * 1024 * 10, // 10MB maximum size allowed for uploaded files
				 maxRequestSize = 1024 * 1024 * 50) // 50MB overall size of all uploaded files
public class InsertImage extends HttpServlet 
{
	private static final long serialVersionUID = -4011837396284189680L;
	
	static String SAVE_DIR="img";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");

		out.write("Error: GET method is used but POST method is required");
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String savePath=request.getServletContext().getRealPath("") + File.separator + SAVE_DIR;

		String message="upload =\n";
		
		if(request.getParts()!=null && request.getParts().size()>0)
			for (Part part : request.getParts()) 
			{
				String fileName=extractFileName(part);
				if(fileName!=null && !fileName.equals("")) 
				{
					part.write(savePath + File.separator + fileName);
					System.out.println(savePath + File.separator + fileName);
					message = message + fileName + "\n";
					//TODO Da salvare nel db
				}
				else
					request.setAttribute("error", "Errore: Bisogna selezionare almeno un file");
			}
		
		request.setAttribute("message", message);
		
		RequestDispatcher dispatcher = getServletContext().
				getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
		
	}

	private String extractFileName(Part part) 
	{
		//content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items)
			if (s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
		
		return "";
	}
}
