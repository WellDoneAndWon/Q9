package com.example.springmvs9;

import com.example.springmvs9.model.Product;
import com.example.springmvs9.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.verify;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService productService;
    @MockBean
    private ProductRepository productRepository;
    @Test
    public void addProductTest() {
        Product product = new Product();
        product.setName("testProduct");
        product.setBought(false);

        when(productRepository.save(product)).thenReturn(product);
        Product result = productService.save(product);
        Assertions.assertNotNull(result);
        Assertions.assertEquals(product, result);
    }

    @Test
    void getAllProduct() {
        Product product1 = new Product();
        product1.setName("testProduct1");
        product1.setBought(false);

        Product product2 = new Product();
        product2.setName("testProduct2");
        product2.setBought(true);

        List<Product> actual = new ArrayList<>();
        actual.add(product1);
        actual.add(product2);
        when(productRepository.findAll()).thenReturn(actual);

        List<Product> expected = productService.getAllProduct();

        Assertions.assertNotNull(expected);
        Assertions.assertEquals(2,expected.size());
        Assertions.assertEquals(actual,expected);
    }
}