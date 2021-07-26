package cl.sentra.testbci.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControlExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ResponseError errorSalida = new ResponseError();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errorSalida.setMensaje(errorMessage);
        });

        return new ResponseEntity<>(errorSalida, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ControlException.class)
    public ResponseEntity<Object> controlException(ControlException ex) {

        ResponseError responseError = ResponseError.builder().mensaje(ex.getMensaje()).build();
        return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> allException(Exception ex) {

        ResponseError responseError = ResponseError.builder().mensaje(ex.getMessage()).build();
        return new ResponseEntity<>(responseError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
