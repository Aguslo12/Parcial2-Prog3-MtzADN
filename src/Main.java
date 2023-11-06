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
            String input = scan.next();

            // Validar que la entrada solo contenga caracteres válidos (A, T, C, G)
            if (isValidDNASequence(input) && (isValidADNLength(input))) {
                adnArr[i] = input;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese solo A, T, C o G y 6 caracteres.");
                i--; // Repetir la entrada
            }
        }

        // Convertimos el Array En Matriz
        String[][] adnMtz = ConvArrEnMtz(adnArr);


        // Verificamos si el array està vacio
        if (adnArr.length != 6){
            System.out.println("Cantidad de ADN invalido");
        } else { // Si no esta vacio lo mostramos por pantalla
            for (int i = 0; i < 6; i++) {
                System.out.println(" ");
                for (int j = 0; j < 6; j++) {
                    System.out.print("["+adnMtz[i][j]+"] ");
                }
            }

            System.out.println("\nVerificando si el ADN es mutante o no");
            System.out.println("...");
            System.out.println("\n");
            if (isMutant(adnMtz)){ // Verificamos si es mutante o no
                System.out.println("ES UN MUTANTE!!!");
            } else {
                System.out.println("No es mutante");
            }
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
        if (input.length() < 6){
            return false;
        } else return true;
    }

    public static boolean isValidDNASequence(String input) {
        // Usar una expresión regular para validar la entrada
        return input.matches("^[ATCG]+$");
    }

    public static boolean isMutant(String[][] adn){
        int repes = 0;
        for (int i = 0; i < 6; i++) { // Recorremos las filas del ADN
            for (int j = 0; j < 6; j++) { // Recorremos las columnas del ADN
                int cont;
                try{
                    if (Objects.equals(adn[i][j], adn[i + 1][j + 1])) { // Vemos si hay 4 letras iguales en forma oblicua hacia la derecha
                        cont = 0;
                        for (int k = 1; k < 3; k++) {
                            if (Objects.equals(adn[i + 1][j + 1], adn[i + 1 + k][j + 1 + k])){
                                cont = cont + 1;

                            }
                            if (cont == 2)
                                repes = repes + 1;
                        }
                    }
                    if ((Objects.equals(adn[i][j], adn[i - 1][j - 1]))) { // Vemos si hay 4 letras iguales en forma oblicua hacia la izquierda
                        cont = 0;
                        for (int k = 1; k < 3; k++) {
                            if (Objects.equals(adn[i - 1][j - 1], adn[i - 1 - k][j - 1 - k])){
                                cont = cont + 1;

                            }
                            if (cont == 2)
                                repes = repes + 1;
                        }
                    }
                    if (Objects.equals(adn[i][j], adn[i][j+1])) { // Vemos si hay 4 letras iguales en forma horizontal
                        cont = 0;
                        for (int k = 1; k < 3; k++) {
                            if (Objects.equals(adn[i][j+1], adn[i][j+1+k])){
                                cont = cont + 1;
                            }
                            if (cont == 2) {
                                repes = repes + 1;
                            }
                        }
                    }
                    if (Objects.equals(adn[i][j], adn[i+1][j])){ // Vemos si hay 4 letras iguales en forma vertical
                        cont = 0;
                        for (int k = 1; k < 3; k++) {
                            if (Objects.equals(adn[i+1][j], adn[i+1+k][j])){
                                cont++;
                            }
                            if (cont == 2) {
                                repes = repes + 1;
                            }
                        }
                    }
                } catch (Exception ignored){
                }
            }
        }
        if (repes >= 2){ // Si se vieron 2 o mas repeticiones de 4 letras, se dice que es mutante o no
            return true;
        } else return false;
    }
}