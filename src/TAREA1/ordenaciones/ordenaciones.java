package TAREA1.ordenaciones;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ordenaciones {

    private static Path nuevo_archivo(Path ruta, String coletilla) throws IOException {
        String output_nombre = ruta.getFileName().toString().substring(0, ruta.getFileName().toString().lastIndexOf('.')) + coletilla;
        Path output = ruta.getParent().resolve(output_nombre);
        Files.deleteIfExists(output);
        Files.createFile(output);
        return output;
    }

    private static void escribir_contenido(Path output, List<String> contenido) throws IOException{
        for (String linea : contenido) {
            Files.writeString(output, linea, StandardOpenOption.APPEND);
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Ruta del .txt a ordenar: ");
        Scanner entrada = new Scanner(System.in);
        Path ruta = Paths.get(entrada.nextLine());

        System.out.print("¿Tipo de ordenación? [A(sdencente)/d(escendente)]");
        Scanner entrada_ordenacion = new Scanner(System.in);
        Boolean ascendente = entrada_ordenacion.nextLine() != "d" ? Boolean.TRUE : Boolean.FALSE;

        System.out.print("¿Distinción entre mayúsculas y minúsculas? [S/n]");
        Scanner entrada_case = new Scanner(System.in);
        Boolean case_sensitive = entrada_case.nextLine() != "n" ? Boolean.TRUE : Boolean.FALSE;

        if (ascendente) {
            if (case_sensitive) {
                Path output = nuevo_archivo(ruta, "_asc_case.txt");
                //Files.readAllLines(ruta).stream().sorted().forEach(linea -> Files.writeString(output, linea, StandardOpenOption.APPEND)); Unhandled exception??????
                List<String> contenido = Files.readAllLines(output).stream().sorted().toList();
                escribir_contenido(output, contenido);

            } else {
                Path output = nuevo_archivo(ruta, "_asc_non_case.txt");
                List<String> contenido = Files.readAllLines(output).stream().sorted(String.CASE_INSENSITIVE_ORDER).toList();
                escribir_contenido(output, contenido);

            }

        } else {
            if (case_sensitive) {
                Path output = nuevo_archivo(ruta, "_des_case.txt");
                List<String> contenido = Files.readAllLines(output).stream().sorted().toList().reversed();
                escribir_contenido(output, contenido);
            } else {
                Path output = nuevo_archivo(ruta, "_des_non_case.txt");
                List<String> contenido = Files.readAllLines(output).stream().sorted(String.CASE_INSENSITIVE_ORDER).toList().reversed();
                escribir_contenido(output, contenido);
            }

        }
    }
}
