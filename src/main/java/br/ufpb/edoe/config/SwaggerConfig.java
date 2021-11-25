package br.ufpb.edoe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Docket swagger() {
    return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("br.ufpb.edoe"))
        .paths(PathSelectors.any()).build().apiInfo(apiInfo()).tags(
            new Tag("Descritor", "Requisições relacionadas a criação, atualização, remoção e listagem de descritores"),
            new Tag("Item", "Requisições relacionadas a criação, atualização, remoção e listagem de items"),
            new Tag("Login", "Requisições relacionadas ao login de um usuário"),
            new Tag("Usuário", "Requisições relacionadas a criação, atualização, remoção de usuários"));
  }

  @Bean
  UiConfiguration uiConfig() {
    return UiConfigurationBuilder.builder().docExpansion(DocExpansion.LIST).build();
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("eDoe Api")
        .description("É uma api desenvolvida com o objetivo de gerenciar doações no Campus IV da UFPB").version("1.2")
        .build();
  }

}
