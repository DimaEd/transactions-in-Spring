package com.ednach.app.service.impl;

import com.ednach.app.model.Product;
import com.ednach.app.repository.ProductRepository;
import com.ednach.app.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Transactional(isolation = Isolation.SERIALIZABLE)
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);

    }

}
