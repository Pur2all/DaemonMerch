package model;

public class CreditCard
{
	private String CVV, expireDate, number;
	
	public CreditCard()
	{
	}
	
	public String getCVV()
	{
		return CVV;
	}
	
	public String getExpireDate()
	{
		return expireDate;
	}
	
	public String getNumber()
	{
		return number;
	}
	
	public void setCVV(String aCVV)
	{
		CVV=aCVV;
	}
	
	public void setExpireDate(String anExpireDate)
	{
		expireDate=anExpireDate;
	}
	
	public void setNumber(String aNumber)
	{
		number=aNumber;
	}
}
