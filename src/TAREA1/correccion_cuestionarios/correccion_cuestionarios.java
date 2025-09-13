package TAREA1.correccion_cuestionarios;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;

public class correccion_cuestionarios {
    private static Double corrector(String respuestas_correctas, String respuestas_alumno){
        Double puntuacion = 0d;
        for (int i = 0; i < respuestas_correctas.length(); i++){
            if (respuestas_alumno.charAt(i) == respuestas_correctas.charAt(i)){
                puntuacion = puntuacion + 0.5d;
            } else if (respuestas_alumno.charAt(i) == ' '){
                ;
            } else {
                puntuacion = puntuacion - 0.15d;
            }
        }

        if (puntuacion < 0){
            return 0d;
        } else if (puntuacion > 10) {
            return 10d;
        } else {
            return puntuacion;
        }
    }

    private static void puntuador(String alumno, Double puntuacion){
        StringBuilder output = new StringBuilder();
        output.append("Alumno: ").append(alumno).append("\t| Calificación: ");

        if (puntuacion < 4.99d){
            output.append("Suspenso").append(" (").append(puntuacion).append(")");
        } else if (puntuacion >= 5d && puntuacion < 6d){
            output.append("Aprobado").append(" (").append(puntuacion).append(")");
        } else if (puntuacion >= 6d && puntuacion < 7d){
            output.append("Bien").append(" (").append(puntuacion).append(")");
        } else if (puntuacion >= 7d && puntuacion < 8.5d){
            output.append("Notable").append(" (").append(puntuacion).append(")");
        } else {
            output.append("Excelente").append(" (").append(puntuacion).append(")");
        }

        System.out.println(output.toString());
    }

    private static void porcentajes(Collection<Double> values){
        DecimalFormat df = new DecimalFormat("#.##");
        HashMap<String, Double> nota_cantidad = new HashMap<>(Map.of(
                "Suspenso", 0d,
                "Aprobado", 0d,
                "Bien", 0d,
                "Notable", 0d,
                "Excelente", 0d
        ));

        for (Double value : values){
            if (value < 4.99d){
                nota_cantidad.put("Suspenso", nota_cantidad.get("Suspenso") + 1);
            } else if (value >= 5d && value < 6d){
                nota_cantidad.put("Aprobado", nota_cantidad.get("Aprobado") + 1);
            } else if (value >= 6d && value < 7d){
                nota_cantidad.put("Bien", nota_cantidad.get("Bien") + 1);
            } else if (value >= 7d && value < 8.5d){
                nota_cantidad.put("Notable", nota_cantidad.get("Notable") + 1);
            } else {
                nota_cantidad.put("Excelente", nota_cantidad.get("Excelente") + 1);;
            }
        }

        StringBuilder output = new StringBuilder();
        for (String nota : nota_cantidad.keySet()){
            nota_cantidad.put(nota, Double.valueOf(df.format(nota_cantidad.get(nota)/values.toArray().length*100)));
            output.append(nota).append(": ").append(nota_cantidad.get(nota)).append("%\n");
        }
        System.out.println(output.toString());
    }

    public static void main(String[] args) throws IOException {
        System.out.print("Ruta del .txt a evaluar: ");
        Scanner entrada = new Scanner(System.in);
        Path ruta = Paths.get(entrada.nextLine());

        List<String> contenidos_cuestionarios = Files.readAllLines(ruta);
        final String RESPUESTAS_CORRECTAS = contenidos_cuestionarios.getFirst();

        HashMap<String, Double> cuestionarios = new HashMap<>();
        for (int i = 1; i < contenidos_cuestionarios.size(); i++) {
            String linea = contenidos_cuestionarios.get(i);
            if (!linea.isEmpty()) {
                String[] alumno_respuesta = linea.split(" ", 2);
                cuestionarios.put(alumno_respuesta[0], corrector(RESPUESTAS_CORRECTAS, alumno_respuesta[1]));
            }
        }

        for (String alumno : cuestionarios.keySet().stream().sorted().toList()) {
            puntuador(alumno, cuestionarios.get(alumno));
        }

        porcentajes(cuestionarios.values());
    }
}
