package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.entities.Turno;
import com.example.exceptions.InvalidTurno;
import com.example.persistence.GenericoJPA;

import java.time.LocalDate;
import java.util.List;

public class TurnoController {
    GenericoJPA<Turno> genericoJPA;

    public TurnoController() {
        this.genericoJPA = new GenericoJPA<>(Turno.class);
    }

    //Todos los turnos de cada ciudadano
    public List<Turno> findAll() {
        return genericoJPA.findAllGenerico();
    }

    public void create(String fecha, String descripcion, String estado, Ciudadano ciudadano) {
        //Elegir el estado, nos pide una enumeración en el objeto Turno
        Turno.TipoEstado nombreEstado = estado.equalsIgnoreCase("ESPERA") ?  Turno.TipoEstado.ESPERA:  Turno.TipoEstado.ATENDIDO;

        //Hacer una excepción para evitar los required
        try {
            validacionTurno(fecha, descripcion);
            Turno t = new Turno(null, LocalDate.parse(fecha), descripcion, nombreEstado, ciudadano);
            genericoJPA.createGenerico(t);
        } catch (InvalidTurno e) {
            System.out.println(e.getMessage());
        } finally {// Finaliza el try
            System.out.println("Operación finalizada");
        }
    }

    //Validación de datos para evitar la inserción
    private void validacionTurno(String fecha, String descripcion) throws InvalidTurno {

        if(fecha.isEmpty() || descripcion.isEmpty()){
            throw new InvalidTurno("No puedes dejar campos vacíos");
        }
    }
}
