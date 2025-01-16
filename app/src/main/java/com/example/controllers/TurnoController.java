package com.example.controllers;

import com.example.entities.Turno;
import com.example.persistence.GenericoJPA;

import java.util.List;

public class TurnoController {
    GenericoJPA<Turno> genericoJPA;

    public TurnoController() {
        this.genericoJPA = new GenericoJPA<>(Turno.class);
    }


    public List<Turno> findAll() {
        return genericoJPA.findAllGenerico();
    }
}
