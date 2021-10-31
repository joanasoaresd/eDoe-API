package br.ufpb.edoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.LoginResponseDTO;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.security.JWTSecurity;

@RestController
@RequestMapping("/api")
public class LoginController {

  @Autowired
  private JWTSecurity jwtSecurity;

  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody User user) {
    return new ResponseEntity<>(jwtSecurity.authenticate(user), HttpStatus.OK);
  }
}
