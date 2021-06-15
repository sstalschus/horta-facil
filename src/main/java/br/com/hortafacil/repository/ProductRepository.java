package br.com.hortafacil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hortafacil.model.Product;

public interface ProductRepository extends JpaRepository<Product, String> {

  public Optional<Product> findByNameIgnoreCase(String name);
}
