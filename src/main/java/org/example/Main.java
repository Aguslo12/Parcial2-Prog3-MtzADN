package org.example;

import java.sql.SQLOutput;
import java.util.IllegalFormatCodePointException;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        String[] adnArr = new String[6];

        // Creamos el Array
        System.out.println("Ingrese las filas del ADN: ");
        for (int i = 0; i < 6; i++) {
            System.out.println("Fila " + (i + 1) + ":");
            System.out.print("-> ");
            String input = scan.next().toUpperCase();

            // Validar que la entrada solo contenga caracteres válidos (A, T, C, G)
            if (isValidDNASequence(input) && (isValidADNLength(input))) {
                adnArr[i] = input;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese solo A, T, C o G y 6 caracteres.");
                i--; // Repetir la entrada
            }
        }


        for (int i = 0; i < 6; i++) {
            System.out.println(adnArr[i]);
        }

        System.out.println("\nVerificando si el ADN es mutante o no");
        System.out.println("...");
        System.out.println("\n");
        if (isMutant(adnArr)){ // Verificamos si es mutante o no
            System.out.println("ES UN MUTANTE!!!");
        } else {
            System.out.println("No es mutante");
        }

    }

    public static String[][] ConvArrEnMtz(String[] adnArr){
        // Convierte el array de una dimensión en una matriz de 6x6
        String[][] adnMtz = new String[6][6];
        for (int i = 0; i < 6; i++) {
            adnMtz[i] = adnArr[i].split("");
        }// Convierte el array de una dimensión en una matriz de 6x6
        return adnMtz;
    }

    public static boolean isValidADNLength(String input) {
        // Usar un tamaño valido para la entrada
        return input.length() >= 6;
    }

    public static boolean isValidDNASequence(String input) {
        // Usar una expresión regular para validar la entrada
        return input.matches("^[ATCG]+$");
    }

    public static boolean isMutant(String[] adnArr){
        // Convertimos el Array En Matriz
        String[][] adn = ConvArrEnMtz(adnArr);

        int repes = 0;
        int[] dx = {1, 0, 1, 1}; // Direcciones (derecha, abajo, abajo-derecha, arriba-derecha)
        int[] dy = {0, 1, 1, -1};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                String currentChar = adn[i][j];

                for (int dir = 0; dir < 4; dir++) {
                    int cont = 0;

                    for (int k = 1; k <= 3; k++) {
                        int x = i + k * dx[dir];
                        int y = j + k * dy[dir];

                        if (x >= 0 && x < 6 && y >= 0 && y < 6 && adn[x][y].equals(String.valueOf(currentChar))) {
                            cont++;
                        } else {
                            break; // No se encontró una secuencia continua
                        }

                        if (cont == 3) {
                            repes++;
                            break;
                        }
                    }
                }
            }
        }

        return repes >= 2;
    }
}