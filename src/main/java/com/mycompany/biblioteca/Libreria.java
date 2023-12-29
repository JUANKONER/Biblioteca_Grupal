package com.mycompany.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;


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
            if (libro.getAutor().equalsIgnoreCase(autor) || libro.getAutor().contains(autor)) {
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
            if (libro.getNombre().equalsIgnoreCase(titulo) || libro.getNombre().contains(titulo)) {
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

    public void guardarLibrosEnExcel(String rutaArchivo) {
        Workbook libro = new XSSFWorkbook();
        Sheet hoja = libro.createSheet("Libros");

        int rowNum = 0;
        for (Libro libros : listaLibros) {
            Row row = hoja.createRow(rowNum++);
            row.createCell(0).setCellValue(libros.getNombre());
            row.createCell(1).setCellValue(libros.getAutor());
            row.createCell(2).setCellValue(libros.getId());
            row.createCell(3).setCellValue(libros.getUnidadesDisponibles());
        }
    
        try {
            FileOutputStream outputStream = new FileOutputStream(rutaArchivo);
            libro.write(outputStream);
            libro.close();
            outputStream.close();
            System.out.println("Datos de libros guardados en el archivo Excel correctamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void leerLibrosDesdeExcel(String rutaArchivo) {
        try {
            FileInputStream file = new FileInputStream(rutaArchivo); // Ruta al archivo Excel
            Workbook libro = WorkbookFactory.create(file);

            Sheet hoja = libro.getSheetAt(0); // Obtener la primera hoja del libro
            for (Row fila : hoja) {
                String titulo = fila.getCell(0).getStringCellValue();
                String autor = fila.getCell(1).getStringCellValue();
                int id = (int) fila.getCell(2).getNumericCellValue();
                int unidadesDisponibles = (int) fila.getCell(3).getNumericCellValue();

                // Aquí puedes crear un nuevo libro y agregarlo a la lista de libros en tu Libreria
                Libro nuevoLibro = new Libro(id, titulo, autor, unidadesDisponibles);
                listaLibros.add(nuevoLibro);

                // O bien, puedes mostrar la información leída del archivo Excel
                System.out.println("ID: " + id + " - Libro: " + titulo + " - Autor: " + autor + " - Disponibles: " + unidadesDisponibles);
            }

            libro.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


    

