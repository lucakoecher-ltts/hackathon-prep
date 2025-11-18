package com.cariad.hackathon.exception;

public class VehicleAlreadyInEcoModeException extends RuntimeException {
    public VehicleAlreadyInEcoModeException(String message) {
        super(message);
    }
}