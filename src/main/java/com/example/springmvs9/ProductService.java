package com.example.springmvs9;

import com.example.springmvs9.model.Product;
import com.example.springmvs9.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.*;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product save(Product product){

        return productRepository.save(product);
    }

    public Optional<Product> getById(int id){
        return productRepository.findById(id);
    }

    public List<Product> getAllProduct(){
        Iterable<Product> iterable = productRepository.findAll();
        ArrayList<Product> products = new ArrayList<Product>();
        if(iterable != null) {
            for(Product p: iterable) {
                products.add(p);
            }
        }
        return products;
    }
    public  boolean update(Product product){
        if(productRepository.existsById(product.getId())){
            product.setBought(!product.isBought());
            return true;
        }
        return false;
    }

    public boolean delete(int id){
        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
