package com.vazidev.learn.jwa.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.vazidev.learn.jwa.models.Product;


public interface ProductDAO extends CrudRepository <Product, Integer>{
	
	public List<Product> getProductsByproductName(String productName);
	/** public List<Product> getProductsByPrice(double price);
	public List<Product> getProductsByproductNameAndPrice(String productName, double price);
	public List<Product> getProductsByPriceRange(double minPrice, double maxPrice);
	public List<Product> getProductsByproductQuantityRange(int minQty, int maxQty);
	public List<Product> getProductsByStatus(String Status);**/
	
}
