package com.moumini.tarik.restauranttablereservation.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Single table for both user and customer data
@DiscriminatorValue("CUSTOMER") // Used for inheritance strategy
@Getter
@Setter
public class Customer extends User {

  @NotBlank
  @Column(name = "firstname", nullable = false)
  private String firstName;

  @NotBlank
  @Column(name = "lastname", nullable = false)
  private String lastName;

 @NotBlank
 @Column(name = "phone_number", nullable = false, unique = true)
  private String phoneNumber;

  private String address;

  public Customer() {
  }

  public Customer(Long id, String username, String email, String password, Set<Role> roles, String firstName, String lastName, String phoneNumber, String address) {
    super(id, username, email, password, roles);
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.address = address;
  }

}
