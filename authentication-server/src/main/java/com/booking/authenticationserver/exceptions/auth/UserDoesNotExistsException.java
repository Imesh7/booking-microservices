package com.booking.authenticationserver.exceptions.auth;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.List;

public class UserDoesNotExistsException extends  RuntimeException implements GraphQLError {
    public UserDoesNotExistsException(String message){
        super(message);
    }


    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public ErrorClassification getErrorType() {
        return null;
    }
}
