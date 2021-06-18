package br.com.hortafacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hortafacil.mapper.FarmerDTO;
import br.com.hortafacil.model.Farmer;
import br.com.hortafacil.service.FarmerService;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

  @Autowired
  private FarmerService farmerService;

  @GetMapping("/")
  public List<FarmerDTO> list() {
    return this.farmerService.listAllFarmers();
  }

  @PostMapping("/")
  public String create(@RequestBody Farmer farmer) {
    return farmerService.createFarmer(farmer);
  }

  @PostMapping("address/{zipcode}/{farmerEmail}")
  public String setFarmerAddress(@PathVariable String zipcode, @PathVariable String farmerEmail) {
    return farmerService.setFarmerAddress(zipcode, farmerEmail);
  }

  @GetMapping("product/{productName}/{farmerEmail}")
  public void setFarmerProduct(@PathVariable String productName, @PathVariable String farmerEmail) {
    farmerService.setFarmerProduct(productName, farmerEmail);
  }

  @GetMapping("/{city}")
  public List<Object> findFarmersToCity(@PathVariable String city) {
    return farmerService.findFarmersToCity(city);
  }

  @GetMapping("/distance/{origin}/{destiny}")
  public ResponseEntity<String> findFarmersToCitsy(@PathVariable String origin, @PathVariable String destiny) {
    return farmerService.consumingGoogleApi(origin, destiny);
  }

}
