package com.example.springmvs9;

import com.example.springmvs9.model.Product;
import com.example.springmvs9.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductRepository productRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void addProduct() throws Exception {
        Product product = new Product();
        product.setName("testProduct");
        product.setBought(false);
        mockMvc.perform(post("/app/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                        .andDo(print())
                        .andExpect(status().is2xxSuccessful());
    }
    @Test
    public void getProductById () throws Exception{
        this.mockMvc.perform(get("/app/products/2"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
