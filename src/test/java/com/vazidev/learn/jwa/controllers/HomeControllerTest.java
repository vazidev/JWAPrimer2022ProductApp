package com.vazidev.learn.jwa.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(value=MethodOrderer.OrderAnnotation.class)
class HomeControllerTest{
	
	
		private String port;
		private String baseUrl = "http://localhost";
		private URL url;
		//private RestTemplate restTemplate;
		
		@Autowired
		private RestTemplate restTemplate;
		
		@BeforeEach
		void setup() throws Exception {
			url =new URL(baseUrl + ":" + port + "/app/home");
		}
		
		
		@Test
		@Order(1)
		@DisplayName("Testing home rest api")
		void testHome() {
			ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
			String expected = "Hello and Warm Welcome to JWA Testing Primer Session"; //"--Hello and Warm Welcome to the JWA Testing PRIMER Session -- by REVATURE";
			String actual = response.getBody();
			System.out.println(actual);
			assertEquals(expected, actual);
		}

	@Test
	@Order(2)
	void testPay() {
		fail("Not yet implemented");
	}



	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}



}
