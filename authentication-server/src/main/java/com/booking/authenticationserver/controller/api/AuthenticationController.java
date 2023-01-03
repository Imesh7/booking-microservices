package com.booking.authenticationserver.controller.api;
import com.booking.authenticationserver.dto.LoginSuccessDto;
import com.booking.authenticationserver.dto.ResponseWrapper;
import com.booking.authenticationserver.model.User;
import com.booking.authenticationserver.services.UserServices;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;


@Controller
@AllArgsConstructor
@EnableAutoConfiguration
@Component

public class AuthenticationController {

    @Autowired
    UserServices userServices;

    @QueryMapping
    public ResponseEntity<String> login(@Argument String email, @Argument String password){
       return userServices.login(email,password);
    }

    @QueryMapping
    public String getNewAccessToken(@Argument String token){
        return userServices.generateNewAccessToken(token);
    }

    @QueryMapping
    public String validateToken(@Argument String token){
        return "";
    }


    @MutationMapping
    public ResponseEntity<ResponseWrapper> registerUser(@Argument String name, @Argument String email, @Argument String password) throws JsonProcessingException {
       return  userServices.register(new User(name, email, password));
    }

    public User forgotPassword(){

        return new User();
    }


}
