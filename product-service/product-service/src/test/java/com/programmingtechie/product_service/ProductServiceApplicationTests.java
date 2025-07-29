package com.programmingtechie.product_service;

import com.programmingtechie.product_service.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@WebMvcTest
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

//	@MockBean
//	private ProductService productService;

	@Autowired
	private ObjectMapper objectMapper;



}
