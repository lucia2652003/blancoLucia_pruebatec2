package com.example.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Turno {
    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fecha;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Turno TipoTurno;

    public enum TipoTurno{
        ESPERA,
        ATENDIDO
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudadano_id",nullable = false)
    private Ciudadano ciudadano;

    //Constructores

    public Turno() {
    }

    public Turno(Long id, LocalDate fecha, String descripcion, Turno tipoTurno, Ciudadano ciudadano) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        TipoTurno = tipoTurno;
        this.ciudadano = ciudadano;
    }

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Turno getTipoTurno() {
        return TipoTurno;
    }

    public void setTipoTurno(Turno tipoTurno) {
        TipoTurno = tipoTurno;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    //toString()

    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", TipoTurno=" + TipoTurno +
                ", ciudadano=" + ciudadano +
                '}';
    }
}
