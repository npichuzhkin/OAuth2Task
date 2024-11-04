package com.npichuzhkin.OAuth2Task.utils;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final String ERROR_PAGE = "/error";

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFound(Model model) {
        model.addAttribute("errorCode", 404);
        model.addAttribute("errorMessage", "Not Found");
        return ERROR_PAGE;
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String handleAccessDenied(Model model) {
        System.out.println("403");
        model.addAttribute("errorCode", 403);
        model.addAttribute("errorMessage", "Access Denied");
        return ERROR_PAGE;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalServerError(Model model) {
        model.addAttribute("errorCode", 500);
        model.addAttribute("errorMessage", "Internal Server Error");
        return ERROR_PAGE;
    }
}
