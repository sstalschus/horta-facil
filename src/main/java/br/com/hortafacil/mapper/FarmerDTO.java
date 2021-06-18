package br.com.hortafacil.mapper;

import java.util.Set;

import br.com.hortafacil.model.Address;
import br.com.hortafacil.model.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmerDTO {
  private String name;
  private String phone;
  private String email;
  private Address address;
  private Set<Product> product;
}
