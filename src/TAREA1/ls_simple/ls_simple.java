package TAREA1.ls_simple;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.nio.file.*;

public class ls_simple {
    public static void main(String[] args) {
        System.out.println("Ruta de la carpeta a observar:");
        Scanner entrada = new Scanner(System.in);
        Path carpeta = Paths.get(entrada.nextLine());
        StringBuilder resultado = new StringBuilder();

        if (!Files.isDirectory(carpeta) || !Files.exists(carpeta)) {
            System.err.println("La ruta a la carpeta es incorrecta, o la carpeta no existe");
        } else {
            try {
                for (Path archivo : Files.list(carpeta).toList()) {
                    System.out.println(archivo);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
