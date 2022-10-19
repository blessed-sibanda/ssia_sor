package com.bsib.ssia_sor.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity(name = "OTPS")
@Data
public class Otp {
  @Id
  private String username;
  private String code;
}
