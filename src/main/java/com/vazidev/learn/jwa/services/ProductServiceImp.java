package com.vazidev.learn.jwa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.vazidev.learn.jwa.models.Product;
import com.vazidev.learn.jwa.repositories.ProductDAO;


@Service
public class ProductServiceImp implements ProductService {
	
	@Autowired
	ProductDAO productDao;
	
	
	@Override
	public String saveProduct(Product product) {
		if (product.getPrice() < 0 || product.getQuantityOnHand() < 0) {
			//check to see that the price is legitimate and the quantity is available
			return "Product price or qoh cannot be negative";	
		}else {
			productDao.save(product);
			return " Product saved successfully";	
		}
	}
	
	@Override
	public String updateProduct(Product product) {
		if (product.getPrice() < 0 || product.getQuantityOnHand() < 0 ) {
			//check to see that the price is legitimate and the quantity is available
			return "Product price or qoh cannot be negative";	
		}else {
			productDao.save(product);
			return " Product saved successfully";	
		}
	}
	
	
	@Override
	public String deleteProduct(int productId) {
		productDao.deleteById(productId);
		return "Product with product id: " + productId + " deleted successfully !!";	
	}
	
	
	@Override
	public Product getProductById(int productId) {
		Optional<Product> product = productDao.findById(productId);
		return product.get();
	}

	@Override
	public List<Product> getProducts() {
		return (List<Product>) productDao.findAll();
	}

	@Override
	public boolean isProductExists(int productId) {
		Optional<Product> product = productDao.findById(productId);
		return product.isPresent();
	}

	@Override
	public List<Product> getProductsByproductName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByPrice(int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByproductNameAndPrice(String productName, int price) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByPriceRange(int minPrice, int maxPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByproductQuantityRange(int minQty, int maxQty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByStatus(String Status) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	


}
