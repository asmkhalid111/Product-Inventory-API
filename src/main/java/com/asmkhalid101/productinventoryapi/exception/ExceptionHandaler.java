package com.asmkhalid101.productinventoryapi.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandaler {


    @ExceptionHandler(ProductNotFoundException.class)


    // It was like this     @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class) in the Project Class
    // But Aziz Bhai said there was another anotation. And i think its the one i am using. I dont know if its right. But i will use it...

    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }


    @ExceptionHandler(InvalidSkuFormatException.class)
    public ResponseEntity<String> handleInvalidSkuFormatException(InvalidSkuFormatException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(SkuAlreadyExistsException.class)
    public ResponseEntity<String> handleSkuAlreadyExistsException(SkuAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage()); // conflict was recomended by inline completion
    }

}
