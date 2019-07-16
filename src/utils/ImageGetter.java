package utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.sql.rowset.serial.SerialBlob;

import model.bean.Image;

public class ImageGetter
{
	public static Image getImage(HttpServletRequest request) throws IOException, ServletException
	{
		String fileName;
		Image image=new Image();
		for(Part part: request.getParts())
		{
			fileName=extractFileName(part);	
			if(fileName!=null && !fileName.equals("")) 
			{
				try
				{
					image.setImage(new SerialBlob(part.getInputStream().readAllBytes()));
				}
				catch(Exception exception)
				{
					exception.printStackTrace();
				}
				image.setImageName(fileName);
			}
		}
		
		return image;
	}
	
	private static String extractFileName(Part part)
	{
		//content-disposition: form-data; name="file"; filename="file.txt"
		String contentDisp=part.getHeader("content-disposition");
		String[] items=contentDisp.split(";");
		for(String s : items)
			if(s.trim().startsWith("filename"))
				return s.substring(s.indexOf("=") + 2, s.length() - 1);

		return "";
	}
}
