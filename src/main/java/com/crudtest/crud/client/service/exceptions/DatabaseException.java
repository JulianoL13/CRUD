package com.crudtest.crud.client.service.exceptions;

@SuppressWarnings("serial")
public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }
}
