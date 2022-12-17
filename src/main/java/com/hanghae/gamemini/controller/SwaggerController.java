package com.hanghae.gamemini.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping ("/api")
public class SwaggerController {
     
     @GetMapping ("/doc")
     public void redirectSwagger(HttpServletResponse response) throws IOException {
          response.sendRedirect("http://localhost:8080/swagger-ui/index.html");
     }
}
