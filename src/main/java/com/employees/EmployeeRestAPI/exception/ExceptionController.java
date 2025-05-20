package com.employees.EmployeeRestAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler
    public ResponseEntity<EmployeeException> employeeNotFoundException(EmployeeNotFoundException exception){
        EmployeeException employeeException = new EmployeeException();
        employeeException.setStatus(HttpStatus.NOT_FOUND.value());
        employeeException.setMessage(exception.getMessage());
        employeeException.setTimestamps(System.currentTimeMillis());
        return new ResponseEntity<>(employeeException,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public ResponseEntity<EmployeeException> allotherTypeError(Exception exception){
        EmployeeException employeeException = new EmployeeException();
        employeeException.setStatus(HttpStatus.BAD_REQUEST.value());
        employeeException.setMessage(exception.getMessage());
        employeeException.setTimestamps(System.currentTimeMillis());
        return new ResponseEntity<>(employeeException,HttpStatus.BAD_REQUEST);
    }
}
