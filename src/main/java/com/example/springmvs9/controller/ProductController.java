package com.example.springmvs9.controller;

import com.example.springmvs9.model.Product;
import com.example.springmvs9.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/app/products")
public class ProductController {

    ProductService productService;
    @Autowired
    public ProductController(ProductService p){
        productService = p;
    }


    @GetMapping("/")
    public ResponseEntity<List<Product>> read() {
        final List<Product> products = productService.getAllProduct();

        return products != null &&  !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> read(@PathVariable(name = "id") int id) {
        Optional <Product> opt = productService.getById(id);


        return opt.isPresent()
                ? new ResponseEntity<>(opt.get(), HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
//    @PutMapping("/{id}")
//    private ResponseEntity<?> update(@PathVariable("id") int id) {
//        boolean updated = productService.update(productService.getById(id).get());
//
//        return updated
//                ? new ResponseEntity<>(HttpStatus.OK)
//                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
//    }
    @PatchMapping("/{id}")
    private ResponseEntity<?> update(@PathVariable("id") int id) {
        boolean updated = productService.update(productService.getById(id).get());
        productService.save(productService.getById(id).get());

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<?> delete(@PathVariable("id") int id) {
        boolean deleted = productService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
