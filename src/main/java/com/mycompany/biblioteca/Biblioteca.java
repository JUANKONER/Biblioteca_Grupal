package com.mycompany.biblioteca;

import java.util.Scanner;
import java.util.InputMismatchException;
public class Biblioteca {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Libreria libreria = new Libreria();

       int opcion = 0;
        do {
            System.out.println("1. Agregar libro");
            System.out.println("2. Buscar por autor");
            System.out.println("3. Buscar por nombre de libro");
            System.out.println("4. Mostrar todos los libros");
            System.out.println("5. Modificar cantidad de un libro");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            

            try {
                opcion = scanner.nextInt();
                scanner.nextLine();
                
            
            switch (opcion) {
            case 1:

                System.out.println("Ha elegido la opción agregar un nuevo libro");
                System.out.print("Introduzca el ID del nuevo libro : ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduzca el nombre del libro a añadir : ");
                String nombre = scanner.nextLine();
                System.out.print("Introduzca el autor del nuevo libro : ");
                String autor = scanner.nextLine();
                System.out.print("Introduzca las unidades disponibles : ");
                int unidadesDisponibles = scanner.nextInt();
                scanner.nextLine();
                Libro nuevoLibro = new Libro(id, nombre, autor, unidadesDisponibles);
                libreria.agregarLibro(nuevoLibro);
                System.out.println("Libro agregado con éxito.");
                break;

            case 2:
                System.out.println("Introduzca el nombre del autor del libro que quiere buscar: ");
                String autores = scanner.next();
                libreria.buscarPorAutor(autores);
                System.out.println("El titulo del libro es " + libreria.buscarPorAutor(autores));
                break;

            case 3:
                System.out.println("Introduzca el nombre del libro que quiere buscar: ");
                String titulos = scanner.next();
                libreria.buscarPorTitulo(titulos);

                break;

            case 4:
                libreria.mostrarTodosLosLibros();
                break;
            case 5:
                //Modificar cantidad 
                break;
            case 6:
                System.out.println("¡Hasta luego!");
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
        }
        while (opcion !=6);
        scanner.close ();
        }
}

