package br.ufpb.edoe.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

import br.ufpb.edoe.security.JWTSecurity;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.PrematureJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class JWTFilter extends GenericFilterBean {
  private static final int TOKEN_INDEX = 7;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest requisicao = (HttpServletRequest) request;

    String header = requisicao.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED,
          "Token inexistente ou mal formatado!");
      return;
    }

    String token = header.substring(TOKEN_INDEX);
    try {
      Jwts.parser().setSigningKey(JWTSecurity.TOKEN_KEY).parseClaimsJws(token).getBody();
    } catch (SignatureException | ExpiredJwtException | MalformedJwtException | PrematureJwtException
        | UnsupportedJwtException | IllegalArgumentException e) {
      ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
      return;
    }

    chain.doFilter(request, response);

  }

}
