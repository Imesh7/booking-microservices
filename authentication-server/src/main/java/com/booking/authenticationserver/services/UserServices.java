package com.booking.authenticationserver.services;
import com.booking.authenticationserver.dto.LoginSuccessDto;
import com.booking.authenticationserver.dto.ResponseWrapper;
import com.booking.authenticationserver.dto.mapping.LoginSuccessDtoMapper;
import com.booking.authenticationserver.exceptions.auth.EmailAlreadyExistsException;
import com.booking.authenticationserver.exceptions.auth.PasswordNotMatchException;
import com.booking.authenticationserver.exceptions.auth.UserDoesNotExistsException;
import com.booking.authenticationserver.model.User;
import com.booking.authenticationserver.repository.*;
import com.booking.authenticationserver.utils.JwtUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;


@Service
@Component

@RequiredArgsConstructor
//LOGGING
@Slf4j
public class UserServices  {
    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    public ResponseEntity<String> login(String email, String password)  {
      boolean userExists = userRepository.existsByEmail(email).get();

        if (!userExists) {
            throw new UserDoesNotExistsException("User does not Exists");
        }

        User userData = userRepository.findByEmail(email).get();

        boolean isPasswordMatch = passwordEncoder().matches(password,userData.getPassword());

        if (!isPasswordMatch) {
            throw  new PasswordNotMatchException("Password not Match");
        }

        String token = jwtUtils.generateToken(userData);

        return ResponseEntity.ok(token);

    }

    public ResponseEntity<ResponseWrapper> register(@NotNull User user) throws JsonProcessingException {

           boolean existingUser = userRepository.existsByEmail(user.getEmail()).get();
           System.out.println(existingUser);
           if (existingUser) {
               throw new EmailAlreadyExistsException("Email address Already registered");
           }

           String encodePassword = passwordEncoder().encode(user.getPassword());
           user.setPassword(encodePassword);

           userRepository.save(user);
           String token = jwtUtils.generateToken(user);

           LoginSuccessDto loginSuccessDto = LoginSuccessDtoMapper.INSTANCE.convertDto(user);

           objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
      String data =  objectMapper.writeValueAsString(loginSuccessDto);
        return ResponseEntity.ok(new ResponseWrapper(loginSuccessDto,"dd","kk"));
    }

    public String generateNewAccessToken(String oldToken){
        //TODO:Needs to validate user
       return jwtUtils.getNewAccessToken(oldToken);
    }


}
