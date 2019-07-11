package model.bean;

import java.sql.Blob;

public class Image
{
	private String imageName;
	private Blob image;
	
	public Image()
	{
	}
	
	public String getImageName()
	{
		return imageName;
	}
	
	public Blob getImage()
	{
		return image;
	}
	
	public void setImageName(String anImageName)
	{
		imageName=anImageName;
	}
	
	public void setImage(Blob anImage)
	{
		image=anImage;
	}
}
