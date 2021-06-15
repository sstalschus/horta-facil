package br.com.hortafacil.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hortafacil.mapper.FarmerDTO;
import br.com.hortafacil.model.Address;
import br.com.hortafacil.model.Farmer;
import br.com.hortafacil.model.Product;
import br.com.hortafacil.repository.FarmerRepository;
import br.com.hortafacil.shared.AppException;
import br.com.hortafacil.util.EncriptPassword;

@Service
public class FarmerService {

  @Autowired
  private FarmerRepository farmerRepository;

  @Autowired
  AddressService addressService;

  @Autowired
  ProductService productService;

  @Autowired
  private ModelMapper modelMapper;

  public String createFarmer(Farmer farmer) {

    Farmer farmerAlreadyExists = this.farmerRepository.findByEmail(farmer.getEmail());

    if (farmerAlreadyExists != null)
      throw new AppException("Farmer Already Exists");

    this.farmerRepository.save(farmer);

    return "Farmer Created";
  }

  public String setFarmerAddress(String zipcode, String farmerEmail) {
    Address address = this.addressService.findByZipcode(zipcode);

    Farmer farmer = this.farmerRepository.findByEmail(farmerEmail);

    if (address == null || farmer == null)
      throw new AppException("Invalid data");

    farmer.setAddress(address);

    this.farmerRepository.save(farmer);

    return "Add Address in Farmer!";
  }

  public void setFarmerProduct(String productName, String farmerEmail) {
    Product product = this.productService.findByName(productName);

    Farmer farmer = this.farmerRepository.findByEmail(farmerEmail);

    if (farmer == null)
      throw new AppException("Invalid data");

    if (product == null) {
      Product newProduct = new Product();

      newProduct.setName(productName);

      product = this.productService.create(newProduct);
    }

    farmer.getProduct().add(product);

    this.farmerRepository.save(farmer);
  }

  public List<Farmer> findFarmersToCity(String city) {
    return null;
  }

  public List<FarmerDTO> listAllFarmers() {
    return this.farmerRepository.findAll().stream().map(this::toFarmerDTO).collect(Collectors.toList());
  }

  private FarmerDTO toFarmerDTO(Farmer farmer) {
    return modelMapper.map(farmer, FarmerDTO.class);
  }
}
