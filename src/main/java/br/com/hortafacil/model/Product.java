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
public class Product {

  @Id
  private String id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, updatable = false)
  @CreationTimestamp
  private Date createdAt;

  public Product() {
    UUID uuid = UUID.randomUUID();
    if (this.id == null || this.id == "") {
      this.id = uuid.toString();
    }
  }

}
