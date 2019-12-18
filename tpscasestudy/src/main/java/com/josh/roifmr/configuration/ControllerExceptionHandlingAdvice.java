package com.josh.roifmr.configuration;

import com.josh.roifmr.domain.exhange.QuoteGenerationException;
import com.josh.roifmr.domain.exhange.QuoteRequestDateValidationException;
import com.josh.roifmr.dto.RequestError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandlingAdvice {

    Logger log = LoggerFactory.getLogger(ControllerExceptionHandlingAdvice.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RequestError> handleValidationException(MethodArgumentNotValidException exception){
        log.error("Validation exception", exception);
        RequestError returnMessage = new RequestError(HttpStatus.BAD_REQUEST.value(), "Request validation failure");
        exception.getBindingResult().getAllErrors().stream().forEach((v)->{
            returnMessage.getDetails().add(String.format("%s reports:: %s",((FieldError)v).getField(),v.getDefaultMessage()));
        });

        log.error("Validation failure response", returnMessage);
        return new ResponseEntity<RequestError>(returnMessage, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(QuoteRequestDateValidationException.class)
    public ResponseEntity<RequestError> handleQuoteRequestValidationException(QuoteRequestDateValidationException validationException){
        log.error("date validation for quote request", validationException);
        RequestError returnMessage = new RequestError(HttpStatus.NOT_ACCEPTABLE.value(), "Requested quote date range invalid");
        returnMessage.getDetails().add(validationException.getMessage());
        return new ResponseEntity<RequestError>(returnMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QuoteGenerationException.class)
    public ResponseEntity<RequestError> handleQuoteGenerationException(QuoteGenerationException failedQuoteException){
        log.error("failed to retrieve requested quote",failedQuoteException);
        RequestError rtnMessage = new RequestError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to retrieve generated data");
        rtnMessage.getDetails().add(failedQuoteException.getMessage());
        return new ResponseEntity<>(rtnMessage, HttpStatus.I_AM_A_TEAPOT);
    }
}
