package br.com.hortafacil.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.hortafacil.model.Farmer;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, String> {

  public Farmer findByEmail(String email);

  @Query(value = "select name, phone, zipcode, district, street, state, complement, neighborhood, email from farmer "
      + "inner join address on farmer.address_id = address.id where district like %:district", nativeQuery = true)
  public List<Object> findAllCityFarmers(@Param("district") String district);

}
