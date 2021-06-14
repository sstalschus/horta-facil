package br.com.hortafacil.model;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name = "farmer")
public class Farmer {

  @Id
  private String id;
  private String name;
  private String username;
  private String password;
  private String email;
  private String phone;

  public Farmer() {
    UUID uuid = UUID.randomUUID();
    if (this.id == null || this.id == "") {
      this.id = uuid.toString();
    }
  }
}
