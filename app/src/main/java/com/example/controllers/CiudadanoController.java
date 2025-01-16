package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.entities.Turno;
import com.example.persistence.GenericoJPA;

import java.util.List;

public class CiudadanoController {
    GenericoJPA<Ciudadano> genericoJPA;

    public CiudadanoController() {
        this.genericoJPA = new GenericoJPA<>(Ciudadano.class);
    }


    public List<Ciudadano> findAll() {
        return genericoJPA.findAllGenerico();
    }
}
