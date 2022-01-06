package controllers;

import org.springframework.stereotype.Service;

@Service
public class Payment {
	
	public String pay (int amount, String payTo) {
	return "Successfully credited USD :"  + amount + " to " + payTo;
	
	}

}
