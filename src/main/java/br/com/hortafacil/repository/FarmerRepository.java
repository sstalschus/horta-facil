package br.com.hortafacil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hortafacil.model.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, String> {

  public Farmer findByEmail(String email);
}
