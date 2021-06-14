package br.com.hortafacil.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping("/{id}")
  public FarmerDTO findById(@PathVariable("id") String id) {
    return farmerService.findById(id);
  }

  @PostMapping("/{id_address}")
  public String create(@RequestBody Farmer farmer, @PathVariable String id_address) {
    farmerService.createFarmer(farmer, id_address);
    return "Farmer created!";
  }
}
