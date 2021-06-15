package br.com.hortafacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hortafacil.model.Product;
import br.com.hortafacil.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;

  @PostMapping("/")
  public Product create(@RequestBody Product product) {
    return this.productService.create(product);
  }

  @GetMapping("/")
  public List<Product> listAllProducts() {
    return this.productService.findAll();
  }
}
