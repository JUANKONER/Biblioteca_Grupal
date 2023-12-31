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
        cargarDatosDesdeExcel("ListaLibros.xlsx");
    }

    public void agregarLibro(Libro libro) {
        boolean idExistenteEnLista = listaLibros.stream().anyMatch(lib -> lib.getId() == libro.getId());
        boolean idExistenteEnExcel = existeIdEnExcel(libro.getId(), "ListaLibros.xlsx");

        if (idExistenteEnExcel) {
            System.out.println("El ID del libro ya existe. Por favor, elige otro ID único.");
        } else {
            listaLibros.add(libro);
            guardarLibrosEnExcel("ListaLibros.xlsx");
            System.out.println("Libro agregado con éxito.");
        }
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
        try {
            FileInputStream file = new FileInputStream("ListaLibros.xlsx");
            Workbook workbook = new XSSFWorkbook(file);
            Sheet hoja = workbook.getSheetAt(0);

            for (int i = 1; i <= hoja.getLastRowNum(); i++) {
                Row fila = hoja.getRow(i);
                if (fila != null) {
                    int idLibro = (int) fila.getCell(2).getNumericCellValue();
                    if (idLibro == id) {
                        String nombre = fila.getCell(0).getStringCellValue();
                        String autor = fila.getCell(1).getStringCellValue();
                        int unidadesDisponibles = (int) fila.getCell(3).getNumericCellValue();

                        Libro libroEncontrado = new Libro(idLibro, nombre, autor, unidadesDisponibles);
                        file.close();
                        workbook.close();
                        return libroEncontrado;
                    }
                }
            }
            file.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void mostrarTodosLosLibros() {
        if (listaLibros.isEmpty()) {
            System.out.println("La librería está vacía.");
        } else {
            boolean librosEncontrados = false;
            System.out.println("-------------------------------------");
            System.out.println("Lista de libros en la librería:");
            for (Libro libro : listaLibros) {
                if (!libro.getNombre().isEmpty()) {
                    librosEncontrados = true;
                    System.out.println("-------------------------------------");
                    System.out.println("ID: " + libro.getId());
                    System.out.println("Nombre: " + libro.getNombre());
                    System.out.println("Autor: " + libro.getAutor());
                    System.out.println("Unidades Disponibles: " + libro.getUnidadesDisponibles());
                    System.out.println("-------------------------------------");
                }
            }
            if (!librosEncontrados) {
                System.out.println("La librería está vacía.");
            }
        }
    }

    public void ModificarUnidades(Libro libro, int cantidad) {
        int unidadesActuales = libro.getUnidadesDisponibles();
        libro.setUnidadesDisponibles(unidadesActuales + cantidad);
        actualizarUnidadesEnExcel(libro.getId(), libro.getUnidadesDisponibles(), "ListaLibros.xlsx");
    }

    public void guardarLibrosEnExcel(String rutaArchivo) {
        try {
            FileInputStream file = new FileInputStream(rutaArchivo);
            Workbook libro = new XSSFWorkbook(file);
            Sheet hoja = libro.getSheetAt(0);

            int filaActual = hoja.getLastRowNum() + 1;

            for (Libro libroActual : listaLibros) {
                if (!existeIdEnExcel(libroActual.getId(), rutaArchivo)) {
                    Row row = hoja.createRow(filaActual++);
                    row.createCell(0).setCellValue(libroActual.getNombre());
                    row.createCell(1).setCellValue(libroActual.getAutor());
                    row.createCell(2).setCellValue(libroActual.getId());
                    row.createCell(3).setCellValue(libroActual.getUnidadesDisponibles());
                }
            }

            FileOutputStream outputStream = new FileOutputStream(rutaArchivo);
            libro.write(outputStream);

            file.close();
            outputStream.close();
            libro.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrarTodosLosLibrosEnExcel(String rutaArchivo) {
        try {
            FileInputStream file = new FileInputStream(rutaArchivo);
            Workbook libro = new XSSFWorkbook(file);
            Sheet hoja = libro.getSheetAt(0);

            int filas = hoja.getLastRowNum();
            for (int i = 1; i <= filas; i++) {
                hoja.removeRow(hoja.getRow(i));
            }

            listaLibros.clear();

            FileOutputStream outputStream = new FileOutputStream(rutaArchivo);
            libro.write(outputStream);

            file.close();
            outputStream.close();
            libro.close();
            System.out.println("Todos los libros han sido borrados satisfactoriamente.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean existeIdEnExcel(int id, String rutaArchivo) {
        try {
            FileInputStream file = new FileInputStream(rutaArchivo);
            Workbook libro = new XSSFWorkbook(file);

            Sheet hoja = libro.getSheetAt(0);

            for (int i = 1; i < hoja.getPhysicalNumberOfRows(); i++) {
                Row fila = hoja.getRow(i);
                if (fila != null) {
                    Cell cell = fila.getCell(2);
                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                        int idLibro = (int) cell.getNumericCellValue();
                        if (idLibro == id) {
                            libro.close();
                            file.close();
                            return true;
                        }
                    }
                }
            }

            libro.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void actualizarUnidadesEnExcel(int idLibro, int nuevasUnidades, String rutaArchivo) {
        try {
            FileInputStream file = new FileInputStream(rutaArchivo);
            Workbook libro = new XSSFWorkbook(file);
            Sheet hoja = libro.getSheetAt(0);

            for (int i = 1; i <= hoja.getLastRowNum(); i++) {
                Row fila = hoja.getRow(i);
                if (fila != null) {
                    int id = (int) fila.getCell(2).getNumericCellValue();
                    if (id == idLibro) {
                        fila.getCell(3).setCellValue(nuevasUnidades);
                        break;
                    }
                }
            }
            FileOutputStream outputStream = new FileOutputStream(rutaArchivo);
            libro.write(outputStream);
            file.close();
            outputStream.close();
            libro.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cargarDatosDesdeExcel(String rutaArchivo) {
        try {
            FileInputStream file = new FileInputStream(rutaArchivo);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet hoja = workbook.getSheetAt(0);

            listaLibros.clear();

            for (int i = 1; i <= hoja.getLastRowNum(); i++) {
                Row fila = hoja.getRow(i);
                if (fila != null) {
                    int id = (int) fila.getCell(2).getNumericCellValue();
                    String nombre = fila.getCell(0).getStringCellValue();
                    String autor = fila.getCell(1).getStringCellValue();
                    int unidadesDisponibles = (int) fila.getCell(3).getNumericCellValue();

                    Libro libro = new Libro(id, nombre, autor, unidadesDisponibles);
                    listaLibros.add(libro);
                }
            }

            file.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
