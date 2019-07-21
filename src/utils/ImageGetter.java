package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import model.bean.Image;

@MultipartConfig(fileSizeThreshold=1024 * 1024 * 2,	// 2MB after which the file will be temporarily stored on disk
				maxFileSize=1024 * 1024 * 10,		// 10MB maximum size allowed for uploaded files
				maxRequestSize=1024 * 1024 * 50)	// 50MB overall size of all uploaded files
public class ImageGetter
{
	public static Image getImage(HttpServletRequest request) throws IOException, ServletException
	{
		//TODO Fai tornare un array di immagini che ne possono essere inserite più di una
		String fileName;
		Image image=new Image();
		for(Part part: request.getParts())
		{
			fileName=extractFileName(part);	
			if(fileName!=null && !fileName.equals("")) 
			{
				try
				{
					image.setImage(readAllBytes(part.getInputStream()));
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
	
	public static byte[] readAllBytes(InputStream inputStream) throws IOException
	{
		byte[] buffer=new byte[8192];
	    int bytesRead;
	    ByteArrayOutputStream output=new ByteArrayOutputStream();
	    
	    while((bytesRead=inputStream.read(buffer))!=-1)
	        output.write(buffer, 0, bytesRead);
	    
	    return output.toByteArray();
	}
}
