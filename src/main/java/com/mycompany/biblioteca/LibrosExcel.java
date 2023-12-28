package com.mycompany.biblioteca;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;

public class LibrosExcel {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("\\Biblioteca-Grupal"); // Ruta al archivo Excel
            Workbook libro = WorkbookFactory.create(file);

            Sheet hoja = libro.getSheetAt(0); // Obtener la primera hoja del libro
            for (Row fila : hoja) {
                String titulo = fila.getCell(0).getStringCellValue();
                String autor = fila.getCell(1).getStringCellValue();
                int id = (int) fila.getCell(2).getNumericCellValue();
                int unidadesDisponibles = (int) fila.getCell(3).getNumericCellValue();
                
                // Aquí puedes procesar los datos como desees, por ejemplo, agregarlos a tu simulación de biblioteca
                System.out.println("ID: " + id + " - Libro: " + titulo + " - Autor: " + autor + " - Disponibles: " + unidadesDisponibles);
            }

            libro.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}