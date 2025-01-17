package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.entities.Turno;
import com.example.exceptions.InvalidTurno;
import com.example.persistence.GenericoJPA;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
        Turno.TipoEstado nombreEstado = elegirEnumeracion(estado);

        //Hacer una excepción para evitar poner el HTML required (campos obligatorios)
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
        //Campos vacíos
        if(descripcion.isEmpty()){
            throw new InvalidTurno("No puedes dejar campos vacíos");
        }

        //Fecha anterior a la fecha actual
        if(LocalDate.parse(fecha).isBefore(LocalDate.now())){
            throw new InvalidTurno("No puedes introducir una fecha antes de "+LocalDate.now());
        }
    }

    public List<Turno> filtroTurno(String estado, String fecha) {
        List<Turno> todosTurnos = genericoJPA.findAllGenerico();
        Turno.TipoEstado tipo = elegirEnumeracion(estado);
        List<Turno> filtracion;

        if(fecha.isEmpty()){//Ponemos por defecto la fecha Actual, si el campo está vacío
            filtracion = todosTurnos.stream()
                                    .filter(turno ->
                                            LocalDate.now().isBefore(turno.getFecha())
                                                                && turno.getEstado().equals(tipo))
                                    .collect(Collectors.toList());
        }else{
            filtracion = todosTurnos.stream()
                    .filter(turno ->
                            LocalDate.parse(fecha).isBefore(turno.getFecha())
                                    && turno.getEstado().equals(tipo))
                    .collect(Collectors.toList());
        }

        return filtracion;
    }


    //Presenta un select que coge el value='' de las options, necesitamos un dato de la enumeración
    private Turno.TipoEstado elegirEnumeracion(String estado) {
        return estado.equalsIgnoreCase("ESPERA") ?  Turno.TipoEstado.ESPERA:  Turno.TipoEstado.ATENDIDO;
    }

}
