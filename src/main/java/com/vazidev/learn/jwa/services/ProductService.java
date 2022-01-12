package com.vazidev.learn.jwa.services;

import java.util.List;
import java.util.Optional;

import com.vazidev.learn.jwa.models.*;

//nb we don't annotate an interface, since no actions
public interface ProductService {
	
	
	public String saveProduct(Product product); 
	public String updateProduct(Product product);
	public String deleteProduct(int productId);
	public Product getProduct(int productId);
	public List<Product> getProducts();
	public boolean isProductExists(int productId);
	
	
	//TODO
	public List<Product> getProductsByproductName(String productName);
	public List<Product> getProductsByPrice(double price);
	public List<Product> getProductsByproductNameAndPrice(String productName, double price);
	public List<Product> getProductsByPriceRange(double minPrice, double maxPrice);
	public List<Product> getProductsByproductQuantityRange(int minQty, int maxQty);
	public List<Product> getProductsByStatus(String Status);
	
	

}
