package ch.hrms.assessment.fullstackbackendchallenge.web;

import jakarta.validation.ValidationException;
import jakarta.xml.bind.JAXBException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {ValidationException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {JAXBException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handleJAXBException(JAXBException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RuntimeException.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    ResponseEntity<Object> handleRuntimeException(JAXBException ex, WebRequest request) {
        return handleExceptionInternal(
                ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
}
