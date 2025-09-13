package TAREA1.coches;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class coches {
    public static void main(String[] args) throws IOException {
        System.out.print("Ruta del .txt: ");
        Scanner entrada = new Scanner(System.in);
        Path ruta = Paths.get(entrada.nextLine());

        Path output = ruta.getParent().resolve("marcas.txt");
        Files.deleteIfExists(output);
        Files.createFile(output);

        HashMap<String, List<String>> coches = new HashMap<>();
        for (String coche : Files.readAllLines(ruta)) {
            String[] datos = coche.split(" ", 2);
            if (coches.containsKey(datos[0])){
                List<String> lista_actualizada = coches.get(datos[0]);
                lista_actualizada.add(datos[1]);
                coches.put(datos[0], lista_actualizada);
            } else {
                List<String> nueva_lista = new ArrayList<>();
                nueva_lista.add(datos[1]);
                coches.put(datos[0], nueva_lista);
            }
        }

        for (String marca : coches.keySet().stream().sorted().toList()) {
            Files.writeString(output, marca + ": " + coches.get(marca).stream().sorted().toList().toString().substring(1, coches.get(marca).toString().length() - 1) + "\n", StandardOpenOption.APPEND);
        }
    }
}
