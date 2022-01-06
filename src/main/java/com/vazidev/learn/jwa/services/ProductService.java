package com.vazidev.learn.jwa.services;

import java.util.List;

import com.vazidev.learn.jwa.models.*;

//nb we don't annotate an interface, since no actions
public interface ProductService {
	
	
	public String saveProduct(Product product); 
	public String updateProduct(Product product);
	public String deleteProduct(int productId);
	public Product getProductById(int productId);
	public List<Product> getProducts();
	public boolean isProductExists(int productId);
	
	
	//TODO
	public List<Product> getProductsByproductName(String productName);
	public List<Product> getProductsByPrice(int price);
	public List<Product> getProductsByproductNameAndPrice(String productName, int price);
	public List<Product> getProductsByPriceRange(int minPrice, int maxPrice);
	public List<Product> getProductsByproductQuantityRange(int minQty, int maxQty);
	public List<Product> getProductsByStatus(String Status);
	
	

}
