package model.bean;

public class Image
{
	private String imageName;
	private byte[] image;
	
	public Image()
	{
	}
	
	public String getImageName()
	{
		return imageName;
	}
	
	public byte[] getImage()
	{
		return image;
	}
	
	public void setImageName(String anImageName)
	{
		imageName=anImageName;
	}
	
	public void setImage(byte[] anImage)
	{
		image=anImage;
	}
}
