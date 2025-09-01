package com.vazidev.learn.jwa.controllers;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vazidev.learn.jwa.models.Product;
import com.vazidev.learn.jwa.repositories.ProductDAO;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    ProductDAO productDAO;

    @GetMapping("/info")
    public Map<String, Object> info() {
        Map<String, Object> map = new HashMap<>();
        map.put("status", "ok");
        map.put("time", Instant.now().toString());
        long count = productDAO.count();
        map.put("productCount", count);
        List<String> names = ((List<Product>) productDAO.findAll())
                .stream()
                .map(Product::getProductName)
                .limit(5)
                .collect(Collectors.toList());
        map.put("sampleProducts", names);
        return map;
    }
}

