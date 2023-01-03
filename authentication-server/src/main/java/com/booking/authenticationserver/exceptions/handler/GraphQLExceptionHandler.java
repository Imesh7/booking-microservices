package com.booking.authenticationserver.exceptions.handler;

import com.booking.authenticationserver.exceptions.auth.EmailAlreadyExistsException;
import com.booking.authenticationserver.exceptions.auth.JwtTokenFailedException;
import com.booking.authenticationserver.exceptions.auth.PasswordNotMatchException;
import com.booking.authenticationserver.exceptions.auth.UserDoesNotExistsException;
import graphql.GraphQLError;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;


@Slf4j
@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionResolver {

    @Override
    public Mono<List<GraphQLError>> resolveException(@NotNull Throwable exception, @NotNull DataFetchingEnvironment environment) {
        if (exception instanceof EmailAlreadyExistsException) {
            EmailAlreadyExistsException emailAlreadyExistsException = (EmailAlreadyExistsException) exception;
            return Mono.just(Collections.singletonList(emailAlreadyExistsException));
        }

        if (exception instanceof UserDoesNotExistsException) {
            UserDoesNotExistsException userDoesNotExistsException = (UserDoesNotExistsException) exception;
            return Mono.just(Collections.singletonList(userDoesNotExistsException));
        }

        if (exception instanceof PasswordNotMatchException) {
            PasswordNotMatchException passwordNotMatchException = (PasswordNotMatchException) exception;
            return Mono.just(Collections.singletonList(passwordNotMatchException));
        }

        if (exception instanceof JwtTokenFailedException) {
            JwtTokenFailedException jwtTokenFailedException = (JwtTokenFailedException) exception;
            return Mono.just(Collections.singletonList(jwtTokenFailedException));
        }


        return null;
    }



}
