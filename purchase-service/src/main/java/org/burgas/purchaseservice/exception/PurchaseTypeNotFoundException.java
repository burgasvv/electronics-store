package org.burgas.purchaseservice.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PurchaseTypeNotFoundException extends RuntimeException {

    private final String message;
}
