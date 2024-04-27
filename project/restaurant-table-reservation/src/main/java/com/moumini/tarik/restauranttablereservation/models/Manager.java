package com.moumini.tarik.restauranttablereservation.models;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Single table for both user and manager data
@DiscriminatorValue("MANAGER") // Used for inheritance strategy
public class Manager extends User {

  public Manager() {
  }

  public Manager(Long id, String username, String email, String password, Set<Role> roles) {
    super(id, username, email, password, roles);
  }

}
