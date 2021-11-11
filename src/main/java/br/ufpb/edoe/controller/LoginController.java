package br.ufpb.edoe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufpb.edoe.dto.LoginResponseDTO;
import br.ufpb.edoe.dto.UserLoginDTO;
import br.ufpb.edoe.security.JWTSecurity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "LoginController", produces = MediaType.APPLICATION_JSON_VALUE, tags = { "Login" })
@RestController
@RequestMapping("/auth")
public class LoginController {

  @Autowired
  private JWTSecurity jwtSecurity;

  @ApiOperation(value = "Solicita a efetuação de login de um usuário")
  @PostMapping("/login")
  public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody UserLoginDTO user) {
    return new ResponseEntity<>(jwtSecurity.authenticate(user), HttpStatus.OK);
  }
}
