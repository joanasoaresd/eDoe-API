package br.ufpb.edoe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.ufpb.edoe.filter.JWTFilter;

@SpringBootApplication
public class EdoeApplication {

  @Bean
  public FilterRegistrationBean<JWTFilter> filterJwt() {
    FilterRegistrationBean<JWTFilter> filterRB = new FilterRegistrationBean<>();
    filterRB.setFilter(new JWTFilter());
    filterRB.addUrlPatterns("/users/role", "/items");
    return filterRB;
  }

  public static void main(String[] args) {
    SpringApplication.run(EdoeApplication.class, args);
  }

}
