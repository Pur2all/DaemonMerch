package model.bean;

import java.util.ArrayList;

public class Cart
{
	private ArrayList<Product> productList;
	
	public Cart()
	{
		productList=new ArrayList<Product>();
	}
	
	public int getNumberOfProduct()
	{
		return productList.size();
	}
	
	public void addProduct(Product product)
	{
		productList.add(product.clone());
	}
	
	public Product getProduct(int index)
	{
		return productList.get(index).clone();
	}
	
	public Product removeProduct(int index)
	{
		return productList.remove(index);
	}
}
