package org.burgas.storeservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StoreNotFoundException extends RuntimeException {

    private final String message;
}
