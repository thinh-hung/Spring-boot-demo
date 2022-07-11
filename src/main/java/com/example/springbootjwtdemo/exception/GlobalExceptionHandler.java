package com.example.springbootjwtdemo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex,WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getLocalizedMessage(), webRequest.getDescription(false),HttpServletResponse.SC_BAD_REQUEST);
        new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(400).body(errorDetails);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> usernameNotFoundException(UsernameNotFoundException ex,WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getLocalizedMessage(), webRequest.getDescription(false),HttpServletResponse.SC_UNAUTHORIZED);
        new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(401).body(errorDetails);
    }
    @ExceptionHandler(ServletException.class)
    public ResponseEntity<?> servletException(ServletException ex,WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getLocalizedMessage(), webRequest.getDescription(false),HttpServletResponse.SC_UNAUTHORIZED);
        new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
        return ResponseEntity.status(401).body(errorDetails);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest webRequest) {
        ErrorDetails errorDetails;
        errorDetails = new ErrorDetails(new Date(), ex.getLocalizedMessage(), webRequest.getDescription(false),HttpServletResponse.SC_BAD_REQUEST);
        new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(400).body(errorDetails);

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> errorintercaptor(Exception ex, WebRequest webRequest) {
        System.out.println("error intercaptor");
        ErrorDetails errorDetails;
        if (ex.getMessage().indexOf("=")>=1){
             errorDetails = new ErrorDetails(new Date(), ex.getMessage().substring(ex.getMessage().lastIndexOf("=")+2,ex.getMessage().length()-5), webRequest.getDescription(false), HttpServletResponse.SC_UNAUTHORIZED);
        }
        else {
             errorDetails = new ErrorDetails(new Date(), ex.getLocalizedMessage(), webRequest.getDescription(false),HttpServletResponse.SC_BAD_REQUEST);
        }
        new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(400).body(errorDetails);

    }
}