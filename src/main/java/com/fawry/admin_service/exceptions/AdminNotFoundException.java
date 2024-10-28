package com.fawry.admin_service.exceptions;

import java.util.NoSuchElementException;

public class AdminNotFoundException extends NoSuchElementException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}
