package com.hanghae.gamemini.service;

import com.hanghae.gamemini.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Validator {
     private final UserRepository userRepository;
}
