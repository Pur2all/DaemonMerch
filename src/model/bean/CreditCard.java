package model.bean;

public class CreditCard implements Cloneable
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
	
	public String toString()
	{
		return getClass().getName() + "[CVV= " + CVV + ", expireDate= " + expireDate + ", number= " + number + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		CreditCard otherCreditCard=(CreditCard) obj;
		
		return CVV.equals(otherCreditCard.CVV) && expireDate.equals(otherCreditCard.expireDate) && number.equals(otherCreditCard.number);
	}
	
	public CreditCard clone()
	{
		try
		{
			CreditCard clone=(CreditCard) super.clone();
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
