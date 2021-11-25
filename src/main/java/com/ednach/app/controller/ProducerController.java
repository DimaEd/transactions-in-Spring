package com.ednach.app.controller;

import com.ednach.app.dto.ProductDto;
import com.ednach.app.model.Product;
import com.ednach.app.service.ProductService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProducerController {
    private final Mapper mapper;

    private final ProductService productService;

    public ProducerController(Mapper mapper, ProductService productService) {
        this.mapper = mapper;
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProductDto>> getAll() {
        final List<Product> products = productService.findAll();
        final List<ProductDto> productDtoList = products.stream()
                .map((product) -> mapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void save(@Valid @RequestBody List<ProductDto> listProductDto) throws InterruptedException {
        for (ProductDto productDto : listProductDto) {
            productDto.setId(null);
            mapper.map(productService.save(mapper.map(productDto, Product.class)), ProductDto.class);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
