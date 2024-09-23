package org.burgas.productservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProductNotFoundException extends RuntimeException{

    private final String message;
}
