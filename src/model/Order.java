package model;

import java.util.ArrayList;

public class Order implements Cloneable
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
	
	public String toString()
	{
		return getClass().getName() + "[id= " + id + ", date= " + date + ", total= " + total + ", state= " + state + 
				", billingAddress= " + billingAddress + ", products= " + products + "]";
	}
	
	public boolean equals(Object obj)
	{
		if(obj==null)
			return false;
		if(getClass()!=obj.getClass())
			return false;
		
		Order otherOrder=(Order) obj;
		
		if(products.size()!=otherOrder.products.size())
			return false;
		else
			for(int i=0; i<products.size(); i++)
				if(!products.get(i).equals(otherOrder.products.get(i)))
					return false;
		
		return id.equals(otherOrder.id) && date.equals(otherOrder.date) && total==otherOrder.total &&
				state==otherOrder.state && billingAddress.equals(otherOrder.billingAddress);
	}
	
	public Order clone()
	{
		try
		{
			Order clone=(Order) super.clone();
			
			clone.billingAddress=billingAddress.clone();
			clone.products=new ArrayList<Product>();
			for(int i=0; i<products.size(); i++)
				clone.products.add(i, products.get(i).clone());
			
			return clone;
		}
		catch(CloneNotSupportedException exception)
		{
			exception.printStackTrace();
		}
		
		return null;
	}
}
