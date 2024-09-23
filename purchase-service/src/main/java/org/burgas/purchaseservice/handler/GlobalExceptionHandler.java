package org.burgas.purchaseservice.handler;

import org.burgas.purchaseservice.exception.PurchaseNotFoundException;
import org.burgas.purchaseservice.exception.PurchaseTypeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PurchaseNotFoundException.class)
    public ResponseEntity<String> handlePurchaseNotFoundException(
            PurchaseNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }

    @ExceptionHandler(PurchaseTypeNotFoundException.class)
    public ResponseEntity<String> handlePurchaseTypeNotFoundException(
            PurchaseTypeNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
    }
}
