package br.com.hortafacil.model;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String phone;

  @ManyToOne
  private Address address;

  @ManyToMany
  private Set<Product> product;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Date createdAt;

  public Farmer() {
    UUID uuid = UUID.randomUUID();
    if (this.id == null || this.id == "") {
      this.id = uuid.toString();
    }
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  public Set<Product> getProduct() {
    return product;
  }

  public void setProduct(Set<Product> product) {
    this.product = product;
  }
}
