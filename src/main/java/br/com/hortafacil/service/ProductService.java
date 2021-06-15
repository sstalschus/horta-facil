package br.com.hortafacil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hortafacil.model.Product;
import br.com.hortafacil.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  ProductRepository productRepository;

  @Autowired
  FarmerService farmerService;

  public Product create(Product product) {

    Product productAlreadyExists = findByName(product.getName());

    if (productAlreadyExists != null)
      return productAlreadyExists;

    return this.productRepository.save(product);
  }

  public List<Product> findAll() {
    return this.productRepository.findAll();
  }

  public Product findByName(String name) {
    Optional<Product> productFind = this.productRepository.findByNameIgnoreCase(name);

    if (productFind.isPresent()) {
      return productFind.get();
    }
    return null;
  }
}
