package com.vazidev.learn.jwa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vazidev.learn.jwa.enumerators.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="products")
public class Product {
	
	@Id
	@Column(nullable=false, unique = true, updatable=false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	private String productName;
	private int quantityOnHand;
	private int price;
	private ProductStatus status;
	
	
	



	public int getProductId() {
		return productId;
	}


	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getQuantityOnHand() {
		return quantityOnHand;
	}


	public void setQuantityOnHand(int quantityOnHand) {
		this.quantityOnHand = quantityOnHand;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public ProductStatus getStatus() {
		return status;
	}


	public void setStatus(ProductStatus status) {
		this.status = status;
	}
	
	
	

}
