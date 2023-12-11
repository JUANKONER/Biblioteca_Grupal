package com.mycompany.biblioteca;

public class GestorLibros {

    public static void ModificarUnidades(Libro libro, int cantidad) {
        int unidadesActuales = libro.getUnidadesDisponibles();
        libro.setUnidadesDisponibles(unidadesActuales + cantidad);
    }

}
