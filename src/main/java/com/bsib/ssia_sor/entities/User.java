package com.bsib.ssia_sor.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "USERS")
@Data
public class User {
  @Id
  private String username;
  private String password;
}
