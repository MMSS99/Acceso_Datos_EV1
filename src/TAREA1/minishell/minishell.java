package TAREA1.minishell;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class minishell {

    private static void cat(Path ruta_actual, String argumento) throws IOException {
        Path archivo = Paths.get(ruta_actual.toAbsolutePath().toString() + "\\" + argumento);
        System.out.println("Estoy en cat con la ruta " + archivo.toString());
        for (String linea : Files.readAllLines(archivo)) {
            System.out.println(linea);

        }
    }

    private static Path cd(Path ruta_actual, String argumento){
        Path nuevaruta;
        argumento = argumento.replace("/", "\\");
        if (argumento.contains("..")){
            //hacia atrás, más de uno va a romper
            nuevaruta = Paths.get(ruta_actual.toAbsolutePath().toString().substring(0,  ruta_actual.toAbsolutePath().toString().lastIndexOf("\\")) + argumento.replace("..", ""));

        } else if (argumento.contains(":")){
            //ruta absoluta
            nuevaruta = Paths.get(argumento);
        } else {
            nuevaruta = Paths.get(ruta_actual.toAbsolutePath().toString() + "\\" + argumento);
        }

        return nuevaruta;
    }

    private static void ls(Path ruta){
        StringBuilder resultado = new StringBuilder();

        if (!Files.isDirectory(ruta) || !Files.exists(ruta)) {
            System.err.println("La ruta a la carpeta es incorrecta, o la carpeta no existe");
        } else {
            try {
                for (Path archivo : Files.list(ruta).toList()) {
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
                            .append("\n");
                }
                System.out.println(resultado.toString());
            } catch (IOException e) {
                // me lo requería el linter
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("— — — MINISHELL — — —");
        Scanner entrada = new Scanner(System.in);
        Path ruta = Paths.get("");
        String entrada_consola;
        String comando;

        while (true){
            System.out.print(ruta.toAbsolutePath() + "\\ > ");
            entrada_consola = entrada.nextLine();
            comando =  entrada_consola.split(" ", 2)[0];
            if (comando.equals("ls")){
                ls(ruta);
            } else  if (comando.equals("cd")){
                ruta = cd(ruta, entrada_consola.split(" ", 2)[1]);
            } else if (comando.equals("cat")){
                cat(ruta, entrada_consola.split(" ", 2)[1]);
            }
        }
    }
}
