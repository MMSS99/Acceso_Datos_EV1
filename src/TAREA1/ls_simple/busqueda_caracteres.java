package TAREA1.ls_simple;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class busqueda_caracteres {
    public static void main(String[] args) {
        System.out.print("Ruta del .txt a leer: ");
        Scanner entrada = new Scanner(System.in);
        Path ruta = Paths.get(entrada.nextLine());

        System.out.println("¿Deseas buscar un carácter especifico? [s/n] ");
        Scanner eleccion = new Scanner(System.in);
        String confirmacion = eleccion.nextLine();

        if (confirmacion.equals("s")) {
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

        } else if (confirmacion.equals("n")) {
            System.out.println("Iniciando conteo del caracter más usado...");


        } else {
            System.out.println("Opción no válida. Abortando.");
        }

    }
    // C:\Users\MM\Desktop\ioreipsum.txt
}
