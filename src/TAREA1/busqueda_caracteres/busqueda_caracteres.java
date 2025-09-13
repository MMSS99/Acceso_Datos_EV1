package TAREA1.busqueda_caracteres;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class busqueda_caracteres {

    public static void buscar_caracteres(Path ruta){
        System.out.println("¿Qué caracter quieres buscar?: ");
        Scanner busqueda = new Scanner(System.in);
        String caracter = busqueda.nextLine();
        if (caracter.length() == 1) {
            try {
                //Files.readAllLines(ruta).forEach(linea -> linea.chars().filter(ch -> ch == caracter.charAt(0)).count());
                long contador = 0;
                for (String linea : Files.readAllLines(ruta)) {
                    if (linea.contains(caracter)) {
                        contador = contador + linea.chars().filter(ch -> ch == caracter.charAt(0)).count();
                    }
                }
                System.out.println("El programa ha encontrado " + contador + " casos del carácter " + caracter);
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            System.out.println("Entrada de caracter inválida. Abortando");
        }
    }

    public static void caracteres_recurrentes(Path ruta){
        System.out.println("Iniciando conteo del caracter más usado...");
        try {
            HashMap<String, Integer> contadores = new HashMap<>();
            for (String linea : Files.readAllLines(ruta)) {
                for (char caracter : linea.toCharArray()) {
                    if (contadores.containsKey(String.valueOf(caracter))) {
                        contadores.put(String.valueOf(caracter), contadores.get(String.valueOf(caracter)) + 1);
                    } else {
                        contadores.put(String.valueOf(caracter), 1);
                    }
                }
            }

            Integer maxocurrencias = Collections.max(contadores.values());
            for (Map.Entry<String, Integer> entry : contadores.entrySet()) {
                if (entry.getValue().equals(maxocurrencias)) {
                    System.out.println("El caracter \"" + entry.getKey() + "\" es el más recurrente, con un total de " + maxocurrencias + " ocurrencias.");
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.print("Ruta del .txt a leer: ");
        Scanner entrada = new Scanner(System.in);
        Path ruta = Paths.get(entrada.nextLine());

        System.out.println("¿Deseas buscar un carácter especifico? [s/n] ");
        Scanner eleccion = new Scanner(System.in);
        String confirmacion = eleccion.nextLine();

        if (confirmacion.equals("s")) {
            buscar_caracteres(ruta);

        } else if (confirmacion.equals("n")) {
            caracteres_recurrentes(ruta);

        } else {
            System.out.println("Opción no válida. Abortando.");
        }

    }
    // C:\Users\MM\Desktop\ioreipsum.txt
}
