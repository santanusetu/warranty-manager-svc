package com.dyson.backend.dto;

public class WarrantyValidationResponse {
    public boolean valid;
    public String message;

    public WarrantyValidationResponse(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
}
