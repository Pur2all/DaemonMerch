package model.bean;

public class WishlistProduct extends Product
{
	private String dateOfAddition;
	private int userID, quantity;
	
	public String getDateOfAddition()
	{
		return dateOfAddition;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public int getQuantity()
	{
		return quantity;
	}
	
	public void setDateOfAddition(String aDateOfAddition)
	{
		dateOfAddition=aDateOfAddition;
	}
	
	public void setUserID(int anUserID)
	{
		userID=anUserID;
	}
	
	public void setQuantity(int aQuantity)
	{
		quantity=aQuantity;
	}
}
