package br.com.hortafacil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hortafacil.model.Address;
import br.com.hortafacil.repository.AddressRepository;
import br.com.hortafacil.shared.AppException;

@Service
public class AddressService {

  @Autowired
  AddressRepository addressRepository;

  public Address createAddress(Address address) {
    Address addressAlreadyExists = this.addressRepository.findByZipcode(address.getZipcode());

    if (addressAlreadyExists != null)
      return addressAlreadyExists;

    return this.addressRepository.save(address);
  }

  public Address findByZipcode(String id) {
    Address address = this.addressRepository.findByZipcode(id);

    if (address == null)
      throw new AppException("Zipcode notFound");

    return address;
  }
}
