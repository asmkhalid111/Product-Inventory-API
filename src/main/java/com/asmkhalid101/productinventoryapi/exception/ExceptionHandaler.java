package com.asmkhalid101.productinventoryapi.exception;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandaler {

    private final Logger log = LoggerFactory.getLogger(ExceptionHandaler.class);

    @ExceptionHandler(ProductNotFoundException.class)


    // It was like this     @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class) in the Project Class
    // But Aziz Bhai said there was another anotation. And i think its the one i am using. I dont know if its right. But i will use it...

    public ResponseEntity<ExceptionResponse> handleProductNotFoundException(ProductNotFoundException e) {
        log.warn("Failed to find product: {}", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage(), HttpStatus.NOT_FOUND.value()));
    }


    @ExceptionHandler(InvalidSkuFormatException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidSkuFormatException(InvalidSkuFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionResponse(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(SkuAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleSkuAlreadyExistsException(SkuAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ExceptionResponse(e.getMessage(), HttpStatus.CONFLICT.value())); // conflict was recomended by inline completion
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

}
