package model;

import java.util.ArrayList;

public class Cart
{
	private ArrayList<Product> productList;
	
	public Cart()
	{
		productList=new ArrayList<Product>();
	}
	
	public void addProduct(Product product)
	{
		productList.add(product.clone());
	}
	
	public Product getProduct(int index)
	{
		return productList.get(index).clone();
	}
	
	public void removeProduct(int index)
	{
		productList.remove(index);
	}
}
