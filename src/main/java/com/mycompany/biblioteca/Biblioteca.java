package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Biblioteca {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        InputOutput inputOutput = new InputOutput();
        Libreria libreria = new Libreria();
        String rutaArchivoExcel = "ListaLibros.xlsx";
        libreria.cargarDatosDesdeExcel(rutaArchivoExcel);
        int opcion = 0;
        List<Integer> idsExistentes = new ArrayList<>();

        boolean esAdmin = false;
        List<String> usuarios = new ArrayList<>();
        usuarios.add("admin");
        usuarios.add("usuario");

        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = inputOutput.leerString();

        if (usuarios.contains(nombreUsuario)) {
            esAdmin = true;
            System.out.println("¡Bienvenido, " + nombreUsuario + " tienes permisos de administrador ");
        } else {
            System.out.println("¡Bienvenido, " + nombreUsuario);
        }

        do {
            System.out.println("-------------------------------------");
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar por autor del libro");
            System.out.println("3. Buscar por nombre del libro");
            System.out.println("4. Mostrar todos los libros");
            System.out.println("5. Modificar cantidad de un libro");
            System.out.println("6. Buscar un libro por ID");
            System.out.println("7. Borrar todo el listado de libros");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");
            System.out.println("\n");

            try {
                opcion = inputOutput.leerEntero();

                switch (opcion) {
                    case 1:
                        if (!esAdmin) {
                            System.out.println("Lo siento, no tienes permiso para agregar libros.");
                            break;
                        }
                        System.out.println("Ha elegido la opción agregar un nuevo libro \n");
                        int id;
                        boolean idRepetido;

                        do {
                            System.out.print("Introduzca el ID del nuevo libro : ");
                            id = inputOutput.leerEntero();
                            idRepetido = idsExistentes.contains(id);
                            if (idRepetido || libreria.existeIdEnExcel(id, "ListaLibros.xlsx")) {
                                System.out.println("El ID del libro ya existe. Por favor, elige otro ID único.");
                            }
                        } while (idRepetido || libreria.existeIdEnExcel(id, "ListaLibros.xlsx"));
                        idsExistentes.add(id);
                        System.out.println("Introduzca el nombre del libro a añadir : ");
                        String nombre = inputOutput.leerString();
                        System.out.print("Introduzca el autor del nuevo libro : ");
                        String autor = inputOutput.leerString();
                        System.out.print("Introduzca las unidades disponibles : ");
                        int unidadesDisponibles = inputOutput.leerEntero();
                        Libro nuevoLibro = new Libro(id, nombre, autor, unidadesDisponibles);
                        libreria.agregarLibro(nuevoLibro);
                        break;

                    case 2:
                        System.out.println("Introduzca el nombre del autor del libro que quiere buscar: ");
                        String autores = inputOutput.leerString();
                        libreria.buscarPorAutor(autores);

                        break;

                    case 3:
                        System.out.println("Introduzca el nombre del libro que quiere buscar: ");
                        String titulos = inputOutput.leerString();
                        libreria.buscarPorTitulo(titulos);

                        break;

                    case 4:
                        libreria.mostrarTodosLosLibros();
                        break;
                    case 5:
                        System.out.println("Introduzca el ID del libro que quiere modificar: ");
                        int idLibroModificar = inputOutput.leerEntero();
                        Libro libroModificar = libreria.buscarLibroPorId(idLibroModificar);

                        if (libroModificar != null) {
                            System.out.println("Introduzca la cantidad a modificar (positiva para aumentar, negativa para disminuir): ");
                            int cantidadModificar = inputOutput.leerEntero();
                            libreria.ModificarUnidades(libroModificar, cantidadModificar);
                            System.out.println("Cantidad modificada con éxito.");
                        } else {
                            System.out.println("El libro con ID " + idLibroModificar + " no existe.");
                        }
                        break;
                    case 6:
                        System.out.println("Introduzca el ID del libro que deseea consultar. ");
                        int LibroAconsultar = inputOutput.leerEntero();
                        Libro libroEncontrado = libreria.buscarLibroPorId(LibroAconsultar);
                        if (libroEncontrado != null) {
                            System.out.println("-------------------------------------");
                            System.out.println("ID: " + libroEncontrado.getId());
                            System.out.println("Nombre: " + libroEncontrado.getNombre());
                            System.out.println("Autor: " + libroEncontrado.getAutor());
                            System.out.println("Unidades Disponibles: " + libroEncontrado.getUnidadesDisponibles());
                            System.out.println("-------------------------------------");
                        } else {
                            System.out.println("No se encontró ningún libro con el ID " + LibroAconsultar);
                        }
                        break;

                    case 7:
                        libreria.borrarTodosLosLibrosEnExcel("ListaLibros.xlsx");
                        break;
                    case 8:
                        System.out.println(" Hasta la proxima ");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("-------------------------------------");
                System.out.println("Por favor, introduce un número válido.");
                System.out.println("-------------------------------------");
                scanner.nextLine();
            }
        } while (opcion
                != 8);
        scanner.close();

    }
}
