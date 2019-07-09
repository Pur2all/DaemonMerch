package model;

public class Product implements Cloneable
{
	private String name, id, description, tag, artistId;
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
	
	public String getArtistId()
	{
		return artistId;
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
	
	public void setArtistId(String anArtistId)
	{
		artistId=anArtistId;
	}
	
	public String toString()
	{
		return getClass().getName() + "[name= " + name + ", id= " + id + ", description= " + description + ", tag= " + tag +
				", price= " + price + ", remaining= " + remaining + ", artistId= " + artistId + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		Product otherProduct=(Product) obj;
		
		return name.equals(otherProduct.name) && id.equals(otherProduct.id) && description.equals(otherProduct.description) &&
				tag.equals(otherProduct.tag) && price==otherProduct.price && remaining==otherProduct.remaining &&
				artistId.equals(otherProduct.artistId);
	}
	
	public Product clone()
	{
		try
		{
			Product clone=(Product) super.clone();
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
