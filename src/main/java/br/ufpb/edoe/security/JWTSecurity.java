package br.ufpb.edoe.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufpb.edoe.dto.LoginResponseDTO;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTSecurity {

  @Autowired
  private UserService userService;
  public static final String TOKEN_KEY = "cvuejeoemaioocnultpqxq";

  public LoginResponseDTO authenticate(User user) {
    if (!userService.validateUser(user)) {
      return new LoginResponseDTO("Email invalido. Login nao realizado.");
    }
    String token = generateToken(user.getEmail());
    return new LoginResponseDTO(token);
  }

  private String generateToken(String email) {
    return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
        .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).compact();
  }

}
