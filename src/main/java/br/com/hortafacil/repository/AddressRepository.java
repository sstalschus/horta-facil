package br.com.hortafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hortafacil.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

  public Address findByZipcode(String zipcode);
}
