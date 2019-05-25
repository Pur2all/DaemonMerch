package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Order
{
	private String id, date;
	private float total;
	private State state;
	private BillingAddress billingAddress;
	private ArrayList<Product> products;
	
	public Order()
	{
		products=new ArrayList<Product>();
	}
	
	public String getId()
	{
		return id;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public float getTotal()
	{
		return total;
	}
	
	public State getState()
	{
		return state;
	}
	
	public BillingAddress getBillingAddress()
	{
		return billingAddress;
	}
	
	public Product getProduct(int index)
	{
		return products.get(index);
	}
	
	public void setId(String anId)
	{
		id=anId;
	}
	
	public void setDate(String aDate)
	{
		date=aDate;
	}
	
	public void setTotal(float aTotal)
	{
		total=aTotal;
	}
	
	public void setState(State aState)
	{
		state=aState;
	}
	
	public void setBillingAddress(BillingAddress aBillingAddress)
	{
		billingAddress=aBillingAddress;
	}
	
	public void setProducts(int index, Product product)
	{
		products.add(product);
	}
	
	public int getProductsQuantity()
	{
		return products.size();
	}
}
