package com.ednach.app.service;

import com.ednach.app.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product save(Product product) throws InterruptedException;

    void deleteById(Long id);

}
