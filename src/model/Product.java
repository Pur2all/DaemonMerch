package model;

public class Product
{
	private String name, id, description, tag;
	private float price;
	private int remaining;
	
	public Product()
	{
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getTag()
	{
		return tag;
	}
	
	public float getPrice()
	{
		return price;
	}
	
	public int getRemaining()
	{
		return remaining;
	}
	
	public void setName(String aName)
	{
		name=aName;
	}
	
	public void setId(String anId)
	{
		id=anId;
	}
	
	public void setDescription(String aDescription)
	{
		description=aDescription;
	}
	
	public void setTag(String aTag)
	{
		tag=aTag;
	}
	
	public void setPrice(float aPrice)
	{
		price=aPrice;
	}
	
	public void setRemaining(int aRemaining)
	{
		remaining=aRemaining;
	}
}
