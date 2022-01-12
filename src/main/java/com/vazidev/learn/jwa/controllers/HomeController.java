package com.vazidev.learn.jwa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping()
public class HomeController {
	
	@Autowired
	Payment payment;
	
	@GetMapping("/home")
	public String home() {
		return "Hello and Warm Welcome to JWA Testing Primer Session";
	}
	
	@RequestMapping("/pay/{amount}/{customer}")
	public String pay(@PathVariable ("amount") int amount, @PathVariable ("customer") String customer) {
		return payment.pay(amount, customer);
	}
	
	/** @RequestMapping("/trx/{details}")
	public String trx(@PathParam Transaction details) {
		return payment.pay(amount, customer);
	} **/

}
