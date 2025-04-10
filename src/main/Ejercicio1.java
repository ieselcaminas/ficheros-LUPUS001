import java.io.File;
import java.util.Scanner;

public class Ejercicio1 {
    public static void main(String[] args) {
        // Comenzamos en la raíz del sistema
        File directorioActual = File.listRoots()[0];
        Scanner scanner = new Scanner(System.in);
        int opcion;
        
        do {
            // Mostrar el directorio actual
            System.out.println("Lista de ficheros y directorios del directorio: " + directorioActual.getAbsolutePath());
            System.out.println("---------------------------------------------------");
            
            // Obtener la lista de archivos/directorios
            File[] contenido = directorioActual.listFiles();
            
            // Opción 0: Directorio padre (si existe)
            if (directorioActual.getParentFile() != null) {
                System.out.println("0.- Directorio padre");
            } else {
                System.out.println("0.- [Raíz del sistema]");
            }
            
            // Mostrar el contenido numerado
            if (contenido != null) {
                for (int i = 0; i < contenido.length; i++) {
                    System.out.print((i + 1) + ".- " + contenido[i].getName());
                    if (contenido[i].isDirectory()) {
                        System.out.println(" <Directorio>");
                    } else {
                        System.out.println(" " + contenido[i].length() + " bytes");
                    }
                }
            }
            
            // Pedir opción al usuario
            System.out.print("\nIntroduce una opción (-1 para salir): ");
            opcion = scanner.nextInt();
            
            // Procesar la opción
            if (opcion == -1) {
                break; // Salir del programa
            } else if (opcion == 0) {
                // Ir al directorio padre si existe
                File padre = directorioActual.getParentFile();
                if (padre != null && padre.canRead()) {
                    directorioActual = padre;
                } else if (padre == null) {
                    System.out.println("Ya estás en la raíz del sistema.");
                } else {
                    System.out.println("No tienes permiso para acceder al directorio padre.");
                }
            } else if (opcion > 0 && opcion <= contenido.length) {
                File seleccionado = contenido[opcion - 1];
                if (seleccionado.isDirectory()) {
                    if (seleccionado.canRead()) {
                        directorioActual = seleccionado;
                    } else {
                        System.out.println("No tienes permiso para acceder a este directorio.");
                    }
                } else {
                    System.out.println("La opción seleccionada es un archivo, no un directorio.");
                }
            } else {
                System.out.println("ERROR: Opción no válida / fuera del límite.");
            }
            
            System.out.println(); // Separador entre iteraciones
            
        } while (true);
        
        scanner.close();
        System.out.println("Programa terminado.");
    }
}