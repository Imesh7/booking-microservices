package com.booking.authenticationserver.repository;

import com.booking.authenticationserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<Boolean> existsByEmail(String email);

    Optional<User> findByEmail(String email);


}
