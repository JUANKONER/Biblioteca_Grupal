package com.mycompany.biblioteca;

import java.util.Scanner;

public class InputOutput {

    private Scanner scanner;

    public InputOutput() {
        scanner = new Scanner(System.in);
    }

    public int leerEntero() {
        int numero = 0;
        boolean entradaCorrecta = false;
        do {
            try {
                String entrada = scanner.nextLine();
                numero = Integer.parseInt(entrada);
                entradaCorrecta = true;
            } catch (NumberFormatException e) {
                System.out.println("Por favor, introduce un número entero válido.");
            }
        } while (!entradaCorrecta);
        return numero;
    }

    public String leerString() {

        return scanner.nextLine();
    }

    public void cerrarScanner() {
        scanner.close();

    }
}
