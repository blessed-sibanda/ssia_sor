package com.bsib.ssia_sor.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bsib.ssia_sor.entities.Otp;
import com.bsib.ssia_sor.entities.User;
import com.bsib.ssia_sor.repos.OtpRepository;
import com.bsib.ssia_sor.repos.UserRepository;
import com.bsib.ssia_sor.utils.GenerateCodeUtil;

@Service
@Transactional
public class UserService {
  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OtpRepository otpRepository;

  public void addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public void auth(User user) {
    Optional<User> o = userRepository.findUserByUsername(user.getUsername());
    if (o.isPresent()) {
      User u = o.get();
      if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
        renewOtp(u);
      } else {
        throw new BadCredentialsException("Bad credentials");
      }
    } else {
      throw new BadCredentialsException("Bad credentials");
    }
  }

  public boolean check(Otp otpToValidate) {
    Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());
    if (userOtp.isPresent()) {
      Otp otp = userOtp.get();
      if (otpToValidate.getCode() != null && otpToValidate.getCode().equals(otp.getCode())) {
        return true;
      }
    }
    return false;
  }

  private void renewOtp(User u) {
    String code = GenerateCodeUtil.generateCode();
    Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());
    if (userOtp.isPresent()) {
      Otp otp = userOtp.get();
      otp.setCode(code);
    } else {
      Otp otp = new Otp();
      otp.setUsername(u.getUsername());
      otp.setCode(code);
      otpRepository.save(otp);
    }
  }
}
