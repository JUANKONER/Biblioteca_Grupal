package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Libreria {

    private List<Libro> listaLibros;

    public Libreria() {
        this.listaLibros = new ArrayList<>();
    }

    // Método para agregar un libro a la lista
    public void agregarLibro(Libro libro) {
        listaLibros.add(libro);
    }

    // Método para buscar libros por autor
    public List<Libro> buscarPorAutor(String autor) {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                librosEncontrados.add(libro);
            }
        }
        return librosEncontrados;
    }

    // Método para buscar libros por título
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (Libro libro : listaLibros) {
            if (libro.getNombre().equalsIgnoreCase(titulo)) {
                librosEncontrados.add(libro);
                System.out.println("-------------------------------------");
                System.out.println("ID: " + libro.getId());
                System.out.println("Nombre: " + libro.getNombre());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Unidades Disponibles: " + libro.getUnidadesDisponibles());
                System.out.println("-------------------------------------");
            }
        }
        return librosEncontrados;
    }

    // Método para mostrar todos los libros
    public void mostrarTodosLosLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("La librería está vacía.");
        } else {
            System.out.println("Lista de libros en la librería:");
            for (Libro libro : listaLibros) {
                System.out.println("ID: " + libro.getId());
                System.out.println("Nombre: " + libro.getNombre());
                System.out.println("Autor: " + libro.getAutor());
                System.out.println("Unidades Disponibles: " + libro.getUnidadesDisponibles());
                System.out.println("-------------------------------------");
            
        
    
            }
        }
    }
    

}
