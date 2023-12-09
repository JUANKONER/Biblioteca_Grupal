package com.mycompany.biblioteca;

public class Libro {

    private int id;
    private String nombre;
    private String autor;
    private int unidadesDisponibles;

    public Libro(int id, String nombre, String autor, int unidadesDisponibles) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

}
