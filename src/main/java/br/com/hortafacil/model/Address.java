package br.com.hortafacil.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
@Entity
public class Address {

  @Id
  private String id;

  @Column(nullable = false)
  private String zipcode;

  @Column(nullable = false)
  private String district;

  @Column(nullable = false)
  private String street;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String neighborhood;

  @Column
  private String complement;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Date createdAt;

  public Address() {
    UUID uuid = UUID.randomUUID();
    if (this.id == null || this.id == "") {
      this.id = uuid.toString();
    }
  }
}
