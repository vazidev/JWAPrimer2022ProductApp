package com.vazidev.learn.jwa.controllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.vazidev.learn.jwa.models.Product;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class ProductControllerTest extends AbstractTest{
	
	String uri = "/product";
	int productId = 999;
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		super.setUp();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	
//testing Save funtionality
	@Test
	@DisplayName("Testing Save Product ")
	@Order(1)
	void testSaveProduct() throws Exception {
		//fail("Not yet implemented");
		Product product = new Product();   //Product(productId, "Dummy Product", 100, 200, );
		System.out.println(product);
		//conver the product object to  JSON format
		String productJSON = super.mapToJson(product);
		System.out.println(productJSON);
		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(productJSON)).andReturn();
				
			int status = mvcResult.getResponse().getStatus();	
			String content = mvcResult.getResponse().getContentAsString();	
				
			assertEquals (201, status);
			assertEquals (content, " Product Saved Succesfully");
			
			
				
				
	}

	
	//Testing update
	@Test
	@DisplayName("Testing update product")
	@Order(2)
	void testUpdateProduct() throws Exception {
		Product product = new Product();
		System.out.println(product);
		//convert the product object into json format
		String productJSON = super.mapToJson(product);
		System.out.println(productJSON);
		MvcResult mvcResult  = mockMvc.perform(
				MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(productJSON)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		
		assertEquals(200, status);
		assertEquals(content, "Product updated successfully");
		
	}
	
	
	//testing the Delete Function
	@Test
	@DisplayName("Testing delete product")
	@Order(5)
	void testDeleteProduct() {
		fail("Not yet implemented");
	}
	


	//Testing the Get All Functionality
	@Test
	@DisplayName("Testing get all products")
	@Order(3)
	void testGetProducts() throws Exception {
		MvcResult mvcResult  = mockMvc.perform(
				MockMvcRequestBuilders.get(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		Product []products = super.mapFromJson(content, Product[].class);
		assertEquals(200, status);
		assertTrue(products.length > 0);
		}

		//Testing the Get single Product functionality
		@Test
		@DisplayName("Testing get single product")
		@Order(4)
		void testGetProduct() {
			fail("Not yet implemented");
		}

}
