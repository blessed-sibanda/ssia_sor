package com.bsib.ssia_sor.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.management.RuntimeErrorException;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class GenerateCodeUtil {
  public static String generateCode() {
    String code;
    try {
      SecureRandom random = SecureRandom.getInstanceStrong();
      int c = random.nextInt(9000) + 1000;
      code = String.valueOf(c);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Problem when generating the random code");
    }
    return code;
  }
}
