package com.hanghae.gamemini.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
     
     @Bean
     public Docket api() {
          return new Docket(DocumentationType.SWAGGER_2)
               .select()
               .apis(RequestHandlerSelectors.basePackage("com.hanghae.gamemini")) // 특정 패키지경로를 API문서화 한다. 1차 필터
               .paths(PathSelectors.any()) // apis중에서 특정 path조건 API만 문서화 하는 2차 필터 "/**"
               .build()
               .groupName("API 1.0.0") // group별 명칭을 주어야 한다.
               .pathMapping("/")
               .apiInfo(apiInfo())
               .securityContexts(Arrays.asList(securityContext()))
               .securitySchemes(Arrays.asList(apiKey()))
               .useDefaultResponseMessages(false); // 400,404,500 .. 표기를 ui에서 삭제한다.
     }
     private ApiInfo apiInfo() {
          return new ApiInfoBuilder()
               .title("겜미니 REST API")
               .description("항해 겜미니 프로젝트 swagger")
               .version("1.0.0")
               .termsOfServiceUrl("")
               .contact(new Contact("gamemini", "https://github.com/HanghaeGamemini/gamemini", "joj1043@kakao.com"))
               .license("")
               .licenseUrl("")
               .build()
               ;
     }
     
     private ApiKey apiKey() {
          return new ApiKey("Authorization", "Authorization", "header");
     }
     
     private SecurityContext securityContext() {
          return springfox
               .documentation
               .spi.service
               .contexts
               .SecurityContext
               .builder()
               .securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
     }
     
     List<SecurityReference> defaultAuth() {
          AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
          AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
          authorizationScopes[0] = authorizationScope;
          return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
     }
}
