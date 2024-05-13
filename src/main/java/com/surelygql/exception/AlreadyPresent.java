package com.surelygql.exception;

import java.util.Map;

public class AlreadyPresent extends AbstractGraphQLException{
    public AlreadyPresent(String message, Map<String, Object> additionParams) {
        super(message, additionParams);
    }
}
