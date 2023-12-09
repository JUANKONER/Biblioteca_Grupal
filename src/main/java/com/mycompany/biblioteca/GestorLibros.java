package com.mycompany.biblioteca;

public class GestorLibros {

    public void ModificarUnidades(Libro libro, int cantidad) {
        int unidadesActuales = libro.getUnidadesDisponibles();
        libro.setUnidadesDisponibles(unidadesActuales + cantidad);
    }

    // Otros métodos para manipular libros
}
