package br.com.hortafacil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hortafacil.model.Address;
import br.com.hortafacil.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

  @Autowired
  AddressService addressService;

  @PostMapping("/")
  public Address createAddress(@RequestBody Address address) {
    return addressService.createAddress(address);
  }

  @GetMapping("/{zipcode}")
  public Address findByZipcode(@PathVariable("zipcode") String zipcode) {
    return addressService.findByZipcode(zipcode);
  }
}
