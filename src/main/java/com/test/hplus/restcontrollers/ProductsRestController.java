package com.test.hplus.restcontrollers;

import com.test.hplus.beans.Product;
import com.test.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
public class ProductsRestController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/hplus/rest/products")
    @ResponseBody
    public List<Product> getProducts() {
        //calll product repo
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @GetMapping("/hplus/rest/product")
    public ResponseEntity<Product> getProductsByRequestParam(@RequestParam("id") String id) {
        return new ResponseEntity<>(productRepository.findById(Integer.valueOf(id)).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/hplus/rest/products/{name}")
    public ResponseEntity<List<Product>> getProductsByPathVariable(@PathVariable("name") String name) {
        return new ResponseEntity<>(productRepository.searchByName(name), HttpStatus.OK);
    }

    @PostMapping("/hplus/rest/product")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(String.valueOf(product.getId()),HttpStatus.OK);
    }
    
    
}
