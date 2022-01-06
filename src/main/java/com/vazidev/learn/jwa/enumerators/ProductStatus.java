package com.vazidev.learn.jwa.enumerators;

public enum ProductStatus {
	
	PRODUCT_ACTIVE ("ACTIVE"),
	PRODUCT_DISCONTINUED ("DISCONTINUED"),
	PRODUCT_ON_HOLD("ON HOLD"),
	PRODUCT_OUT_OF_STOCK("OUT OF STOCK"),
	PRODUCT_RECALLED("RECALLED"),
	PRODUCT_DISCOUNTED("ON SALE"),
	PRODCUCT_ON_BACKORDER("ON BACKORDER"),
	;
	
	ProductStatus(String currentStatus) {
	}

}
