package ejerciciosMetodos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ahorcado {

    private String palabraSecreta;
    private String palabraOculta;
    private String palabraVerdadera = "";
    private int intentos = 5;
    private List<Character> letrasUsadas = new ArrayList<>();//Lista de letras usadas

    public static void main(String[] args) {

        ahorcado juego = new ahorcado();
        juego.iniciarJuego();

        // Ejecuta el juego en un bucle hasta que termine
        while (!juego.juegoTerminado(true)) {
            juego.jugar();
        }
    }

    private void iniciarJuego() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa la palabra secreta: ");
        palabraSecreta = scanner.nextLine();

        palabraOculta = "";
        intentos = 5;
        letrasUsadas.clear();//limpia la lista de letras usadas

        for (int i = 0; i < palabraSecreta.length(); i++) {
            palabraOculta += "_ ";
        }
    }

    private void jugar() {
        System.out.println("Palabra: " + palabraOculta + " Intentos: " + intentos);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresa una letra: ");
        char letra = scanner.nextLine().charAt(0);

        // Verifica si la letra ya ha sido usada
        if (letrasUsadas.contains(letra)) {
            System.out.println("Ya has usado esa letra");
            intentos--;
        } else {
            letrasUsadas.add(letra);
        }

        // Verifica si la letra esta en la palabra secreta
        if (palabraSecreta.indexOf(letra) >= 0) {
            // Si esta, actualiza la palabra oculta
            StringBuilder aux = new StringBuilder(palabraOculta);//StringBuilder para modificar la palabra oculta
            for (int i = 0; i < palabraSecreta.length(); i++) {//Recorre la palabra secreta
                if (palabraSecreta.charAt(i) == letra) {//
                    aux.setCharAt(i * 2, letra); // Multiplico por 2 para tener en cuenta los espacios entre las letras
                    palabraVerdadera += letra;
                }
            }
            palabraOculta = aux.toString();//Convierte el StringBuilder en String
        } else {
            // Si no esta, resta 1 a los intentos
            intentos--;
        }
    }

    private boolean verificarVictoria() {
        boolean victoria = false;
        int contador = 0;
        //si la letra no esta en la palabra secreta aumente intentos fallidos
        for (int i = 0; i < palabraVerdadera.length(); i++) {
            if (palabraVerdadera.indexOf(palabraSecreta.charAt(i)) >= 0) {
                contador++;
            }
        }
        if (contador == palabraSecreta.length()) {
            victoria = true;
        }
        if (intentos < 0) {
            victoria = false;
        }
        return victoria;
    }

    private boolean seguirJugando() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Desea seguir jugando? (s/n)");
        char letra = scanner.nextLine().charAt(0);
        if (letra == 's') {
            main(null);
            return false;
        } else {
            System.out.println("Gracias por jugar");
            return true;
        }
    }

    private boolean juegoTerminado(boolean victoria) {
        victoria = false;
        if (verificarVictoria()) {
            System.out.println("Has ganado! La palabra era: " + palabraSecreta);
            seguirJugando();
            victoria = true;
            return victoria;
        }
        if (intentos <= 0) {
            System.out.println("Has perdido! La palabra era: " + palabraSecreta);
            seguirJugando();
            victoria = true;
            return victoria;
        }
        return victoria;
    }
}
