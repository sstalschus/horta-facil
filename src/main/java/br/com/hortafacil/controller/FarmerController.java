package br.com.hortafacil.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hortafacil.model.Farmer;
import br.com.hortafacil.repository.FarmerRepository;
import br.com.hortafacil.util.EncriptPassword;

@RestController
@RequestMapping("/farmer")
public class FarmerController {

  @Autowired
  private FarmerRepository farmerRepository;

  @GetMapping("/")
  public List<Farmer> list() {
    return this.farmerRepository.findAll();
  }

  @GetMapping("/{id}")
  public boolean listTesteDecode(@PathVariable("id") String id) {

    Optional<Farmer> farmerFind = this.farmerRepository.findById(id);
    if (farmerFind.isPresent()) {
      return EncriptPassword.compare("123456", farmerFind.get().getPassword());
    }
    return false;
  }

  @PostMapping("/")
  public String create(@RequestBody Farmer farmer) {

    farmer.setPassword(EncriptPassword.codify(farmer.getPassword()));
    this.farmerRepository.save(farmer);
    return "Farmer created!";
  }
}
