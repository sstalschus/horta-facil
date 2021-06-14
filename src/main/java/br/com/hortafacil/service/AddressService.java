package br.com.hortafacil.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hortafacil.model.Address;
import br.com.hortafacil.repository.AddressRepository;
import br.com.hortafacil.shared.AddressNotFoundException;

@Service
public class AddressService {

  @Autowired
  AddressRepository addressRepository;

  public Address createAddress(Address address) {
    return this.addressRepository.save(address);
  }

  public Address findById(String id) {
    Optional<Address> addressFind = this.addressRepository.findById(id);
    if (addressFind.isPresent()) {
      return addressFind.get();
    }
    return null;
  }

  public Address findByZipcode(String id) {
    Address address = addressRepository.findByZipcode(id);

    if (address == null)
      throw new AddressNotFoundException("Zipcode notFound");

    return address;
  }
}
