package com.RegistroTareasAPI.RegistroTareas.model;

import org.aspectj.lang.annotation.RequiredTypes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identificador;
    private String descripcion;
    private java.time.LocalDateTime fechaCreacion;
    private boolean vigente;

    public Tarea() {
        this.fechaCreacion = java.time.LocalDateTime.now();
    }
    public Tarea(String descripcion, boolean vigente) {
        this.descripcion = descripcion;
        this.fechaCreacion = java.time.LocalDateTime.now();
        this.vigente = vigente;
    }

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion() {
        this.fechaCreacion = java.time.LocalDateTime.now();
    }

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
}
