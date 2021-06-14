package br.com.hortafacil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hortafacil.mapper.FarmerDTO;
import br.com.hortafacil.model.Address;
import br.com.hortafacil.model.Farmer;
import br.com.hortafacil.repository.FarmerRepository;
import br.com.hortafacil.shared.AddressNotFoundException;
import br.com.hortafacil.util.EncriptPassword;

@Service
public class FarmerService {

  @Autowired
  private FarmerRepository farmerRepository;

  @Autowired
  AddressService addressService;

  @Autowired
  private ModelMapper modelMapper;

  public void createFarmer(Farmer farmer, String id_address) {

    Address address = this.addressService.findById(id_address);

    if (address == null)
      throw new AddressNotFoundException("Address invalid");

    farmer.setAddress(address);
    farmer.setPassword(EncriptPassword.codify(farmer.getPassword()));
    this.farmerRepository.save(farmer);
  }

  public List<FarmerDTO> listAllFarmers() {
    return this.farmerRepository.findAll().stream().map(this::toFarmerDTO).collect(Collectors.toList());
  }

  public FarmerDTO findById(String id) {
    Optional<Farmer> farmerFind = this.farmerRepository.findById(id);
    if (farmerFind.isPresent()) {
      return toFarmerDTO(farmerFind.get());
    }
    return null;
  }

  private FarmerDTO toFarmerDTO(Farmer farmer) {
    return modelMapper.map(farmer, FarmerDTO.class);
  }
}
