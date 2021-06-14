package br.com.hortafacil.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.hortafacil.model.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, String> {

}
