package com.vazidev.learn.jwa.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vazidev.learn.jwa.models.Product;
import com.vazidev.learn.jwa.services.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	//ResponseEntity on SpringEntity
	
	
	@PostMapping
	public ResponseEntity<String>  saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (productService.isProductExists(product.getProductId())) {
			//failed
			responseEntity = new ResponseEntity<String>("Product already Exists", HttpStatus.CONFLICT); //409
		}else {
			result = productService.saveProduct(product);
			if(result.equals("product saved successfully")) {
				responseEntity = new ResponseEntity<String>(result, HttpStatus.CREATED); //201
				
			}else {
				responseEntity = new ResponseEntity<String>(result, HttpStatus.NOT_ACCEPTABLE); //406
			}
		}
		//send appropriate message response
		return responseEntity;
	}
	
	
	
	
	
	@PutMapping
	public ResponseEntity<String>  updateProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (productService.isProductExists(product.getProductId())) {
			//failed
			responseEntity = new ResponseEntity<String>("Product does not Exist ", HttpStatus.CONFLICT); //409
		}else {
			result = productService.updateProduct(product);
			if(result.equals("product updated successfully")) {
				responseEntity = new ResponseEntity<String>(result, HttpStatus.CREATED); //201
				
			}else {
				responseEntity = new ResponseEntity<String>(result, HttpStatus.NO_CONTENT); //204
			}
		}
		//send appropriate message response
		return responseEntity;
	}
	
	
	
	
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String>  deleteProduct(@PathVariable ("productId") int  productId) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (!productService.isProductExists(productId)) {
			//failed
			responseEntity = new ResponseEntity<String>("Product does not Exist ", HttpStatus.NOT_FOUND); //404
		}else {
			result = productService.deleteProduct(productId);
			if(result.equals("product" + productId + " Deleted successfully")) {
				responseEntity = new ResponseEntity<String>(result, HttpStatus.CREATED); //201
				
			}else {
				responseEntity = new ResponseEntity<String>(result, HttpStatus.NOT_ACCEPTABLE); //406
			}
		}
		//send appropriate message response i.e Status code
		return responseEntity;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getProducts(@RequestParam(required = false) String productName, @RequestParam(required = false) String status ){
		ResponseEntity<List<Product>> responseEntity = null;
		List<Product> products = new ArrayList<Product>();
		
		if (productName ==null) {  //verifyr get Prodcut does not include additional requirements
			products = productService.getProducts();
			if (products.size() == 0) {
				//failed
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); //201
			}else {
				responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); //201
				}
		
		}if (status !=null){
			products = productService.getProductsByStatus(status);  //get Products by Status
			
		}else { 
			
			products = productService.getProductsByproductName(productName);   //get Products by Name			
		}
		
		return responseEntity;
		}
		

	
	//Hands On
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable ("productId") int productId){
		ResponseEntity<Product> responseEntity = null;
		Product product = null;
		if (!productService.isProductExists(productId)) {
			//failed
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); //201
		}else {
			product =  productService.getProductById(productId);
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK); //201
			}
		return responseEntity;
		}
	

}
