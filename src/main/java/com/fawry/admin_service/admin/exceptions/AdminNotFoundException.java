package com.fawry.admin_service.admin.exceptions;

import java.util.NoSuchElementException;

public class AdminNotFoundException extends NoSuchElementException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}
