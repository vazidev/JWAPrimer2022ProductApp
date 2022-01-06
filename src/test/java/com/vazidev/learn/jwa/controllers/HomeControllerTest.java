package com.vazidev.learn.jwa.controllers;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)

class HomeControllerTest {
	
	
		private String port;
		private String baseUrl = "http://localhost";
		private URL url;
		//private RestTemplate restTemplate;
		
		@Autowired
		RestTemplate restTemplate;
		
		@BeforeEach
		void setup() throws Exception {
			url =new URL(baseUrl + ":" + port + "/home");
		}
		
		
		@Test
		void testHome() {
			ResponseEntity<String> response = restTemplate.getForEntity(url.toString(), String.class);
			String expected = "--Hello and Warm Welcome to the JWA PRIMER Session -- Revature";
			String actual = response.getBody();
			assertEquals(expected, actual);
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

	@Test
	void testPay() {
		fail("Not yet implemented");
	}

}
