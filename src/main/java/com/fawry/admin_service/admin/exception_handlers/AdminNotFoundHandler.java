package com.fawry.admin_service.admin.exception_handlers;

import com.fawry.admin_service.admin.exceptions.AdminNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AdminNotFoundHandler {
    @ExceptionHandler(AdminNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleAdminNotFoundExceptions(AdminNotFoundException ex) {
        return ex.getMessage();
    }
}
