package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Libreria {

    private List<Libro> listaLibros;

    public Libreria() {
        this.listaLibros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {

        listaLibros.add(libro);
    }

    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> librosEncontrados = new ArrayList<>();
        boolean encontrado = false;
        for (Libro libro : listaLibros) {
            if (libro.getAutor().equalsIgnoreCase(autor)|| libro.getAutor().contains(autor)) {
                librosEncontrados.add(libro);
                encontrado = true;
                System.out.println("-------------------------------------");
                System.out.println("ID: " + libro.getId());
                System.out.println("Nombre: " + libro.getNombre());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Unidades Disponibles: " + libro.getUnidadesDisponibles());
                System.out.println("-------------------------------------");
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros del autor: " + autor);
        }
        return librosEncontrados;
    }

    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> librosEncontrados = new ArrayList<>();
        Boolean encontrado = false;
        for (Libro libro : listaLibros) {
            if (libro.getNombre().equalsIgnoreCase(titulo)) {
                librosEncontrados.add(libro);
                encontrado = true;
                System.out.println("-------------------------------------");
                System.out.println("ID: " + libro.getId());
                System.out.println("Nombre: " + libro.getNombre());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Unidades Disponibles: " + libro.getUnidadesDisponibles());
                System.out.println("-------------------------------------");
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron libros del titulo: " + titulo);
        }
        return librosEncontrados;
    }

    public Libro buscarLibroPorId(int id) {
        for (Libro libro : listaLibros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    public void mostrarTodosLosLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("La librería está vacía.");
        } else {
            System.out.println("-------------------------------------");
            System.out.println("Lista de libros en la librería:");
            for (Libro libro : listaLibros) {
                System.out.println("-------------------------------------");
                System.out.println("ID: " + libro.getId());
                System.out.println("Nombre: " + libro.getNombre());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Unidades Disponibles: " + libro.getUnidadesDisponibles());
                System.out.println("-------------------------------------");

            }
        }
    }
    
    public static void ModificarUnidades(Libro libro, int cantidad) {
        int unidadesActuales = libro.getUnidadesDisponibles();
        libro.setUnidadesDisponibles(unidadesActuales + cantidad);
    }



}
