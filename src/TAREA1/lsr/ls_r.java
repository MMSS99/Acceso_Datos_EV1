package TAREA1.lsr;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ls_r {
    public static void main(String[] args) {
        System.out.println("Ruta de la carpeta a observar:");
        Scanner entrada = new Scanner(System.in);
        Path carpeta = Paths.get(entrada.nextLine());
        StringBuilder resultado = new StringBuilder();

        if (!Files.isDirectory(carpeta) || !Files.exists(carpeta)) {
            System.err.println("La ruta a la carpeta es incorrecta, o la carpeta no existe");
        } else {
            try {
                for (Path archivo : Files.walk(carpeta).toList()) {
                    //BasicFileAttributes atributos = Files.getFileAttributeView(archivo, BasicFileAttributeView.class).readAttributes();
                    resultado.append(Files.isDirectory(archivo) ? "d" : "-")
                            .append(Files.isWritable(archivo) ? "w" : "-")
                            .append(Files.isExecutable(archivo) ? "x" : "-")
                            .append(Files.isReadable(archivo) ? "r" : "-")
                            .append("\t\t")
                            .append(Files.getOwner(archivo))
                            .append("\t\t\t")
                            .append(Files.size(archivo))
                            .append("\t\t\t")
                            .append(Files.getLastModifiedTime(archivo))
                            .append("\t\t")
                            .append(archivo.getFileName())
                            .append("\t\t\t")
                            .append(archivo.toString().replace(carpeta.toString(), ""))
                            .append("\n");
                }
                System.out.println(resultado.toString());
            } catch (IOException e) {
                // me lo requería el linter
                e.printStackTrace();
            }
        }
    }
}

