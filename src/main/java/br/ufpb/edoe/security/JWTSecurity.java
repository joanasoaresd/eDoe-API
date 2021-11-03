package br.ufpb.edoe.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufpb.edoe.dto.LoginResponseDTO;
import br.ufpb.edoe.dto.UserLoginDTO;
import br.ufpb.edoe.entity.User;
import br.ufpb.edoe.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Service
public class JWTSecurity {

  @Autowired
  private UserService userService;
  public static final String TOKEN_KEY = "cvuejeoemaioocnultpqxq";

  public LoginResponseDTO authenticate(UserLoginDTO user) {
    if (!userService.validateUser(user)) {
      return new LoginResponseDTO("Email e/ou senha inválidos. Login não realizado.");
    }
    String token = generateToken(user.getEmail());
    return new LoginResponseDTO(token);
  }

  private String generateToken(String email) {
    return Jwts.builder().setHeaderParam("typ", "JWT").setSubject(email).signWith(SignatureAlgorithm.HS512, TOKEN_KEY)
        .setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)).compact();
  }

  public Optional<String> getUser(String header) {
    if (header == null || !header.startsWith("Bearer ")) {
      throw new SecurityException();
    }

    String token = header.substring(br.ufpb.edoe.filter.JWTFilter.TOKEN_INDEX);

    String subject = null;
    try {
      subject = Jwts.parser().setSigningKey(TOKEN_KEY).parseClaimsJws(token).getBody().getSubject();
    } catch (SignatureException e) {
      throw new SecurityException("Token inválido ou expirado!");
    }
    return Optional.of(subject);
  }

}
