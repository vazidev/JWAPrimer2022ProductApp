package com.vazidev.learn.jwa.repositories;


import org.springframework.data.repository.CrudRepository;
import com.vazidev.learn.jwa.models.Product;


public interface ProductDAO extends CrudRepository <Product, Integer>{

}
