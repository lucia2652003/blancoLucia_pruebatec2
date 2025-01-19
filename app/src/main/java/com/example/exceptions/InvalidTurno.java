package com.example.exceptions;

//Manejo de errores
public class InvalidTurno extends Exception{
    public InvalidTurno() {
    }

    public InvalidTurno(String message) {
        super(message);
    }
}
