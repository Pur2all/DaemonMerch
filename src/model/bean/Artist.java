package model.bean;

import java.util.ArrayList;

public class Artist implements Cloneable
{
	private int id;
	private String name;
	private byte[] logo;
	private byte[][] images;
	private ArrayList<Product> products;
	
	public Artist()
	{
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public byte[] getLogo()
	{
		return logo;
	}
	
	public byte[][] getImages()
	{
		return images;
	}
	
	public ArrayList<Product> getProducts()
	{
		return products;
	}
	
	public void setId(int anId)
	{
		id=anId;
	}
	
	public void setName(String aName)
	{
		name=aName;
	}
	
	public void setLogo(byte[] aLogo)
	{
		logo=aLogo;
	}
	
	public void setImages(byte[][] someImages)
	{
		images=someImages;
	}
	
	public void setProducts(ArrayList<Product> someProducts)
	{
		products=someProducts;
	}
	
	public String toString()
	{
		return getClass().getName() + "[id= " + id + ", name= " + name + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		Artist otherArtist=(Artist) obj;
		
		return id==otherArtist.id && name.equals(otherArtist.name) && logo.equals(otherArtist.logo) && images.equals(otherArtist.images) &&
				products.equals(otherArtist.products);
	}
	
	public Artist clone()
	{
		try
		{
			Artist clone=(Artist) super.clone();
			
			for(int i=0; i<products.size(); i++)
				clone.products.set(i, products.get(i).clone());
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
