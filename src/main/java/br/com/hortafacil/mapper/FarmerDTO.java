package br.com.hortafacil.mapper;

import br.com.hortafacil.model.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmerDTO {

  private String name;
  private String phone;
  private Address address;

}
