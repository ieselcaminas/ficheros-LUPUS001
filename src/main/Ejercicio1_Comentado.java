import java.io.File;
import java.util.Scanner;

public class Ejercicio1_Comentado {
    public static void main(String[] args) {
        // Comenzamos en la raíz del sistema
        File directorioActual = File.listRoots()[0];//obtenemos la raíz del sistemas de archivos ("/" en Linux) [C: en Windows]
        Scanner scanner = new Scanner(System.in);
        int opcion;//almacenamos la opcion que elija el usuario
        
        do {//do-while hasta que el usuario ingrese -1
            // Mostrar el directorio actual
            System.out.println("Lista de ficheros y directorios del directorio: " + directorioActual.getAbsolutePath());
            //con .getAbsolutePath obtenemos la ruta completa del directorio en el que estasmos

            System.out.println("---------------------------------------------------");
            
            // Obtener la lista de archivos/directorios
            File[] contenido = directorioActual.listFiles();
            //listFile devuelve un array de File (File[] contenido) con todos los elementos del directorio en el que nos encontramos
            
            // Opción 0: Directorio padre (si existe)
            if (directorioActual.getParentFile() != null) {//.getParentFile nos lleva al directorio padre (null di estamos en la raíz)
                System.out.println("0.- Directorio padre");
            } else {
                System.out.println("0.- [Raíz del sistema]");//en caso de que sea 'null' (que estemos en la raíz) mostrará este mensaje
            }
            
            // Mostrar el contenido numerado [*5] - explicación mas a detalle abajo
            if (contenido != null) {//recordemos que 'contenido' contiene (jaja) todos los elementos del directorio actual

                for (int i = 0; i < contenido.length; i++) {
                //imprimimos los elementos enumerados (ponemos i+1 para que empiece desde 1 al imprimirse y no 0, para que sea más comprensible para el usuario)
                    System.out.print((i + 1) + ".- " + contenido[i].getName());
                    //contenido[i].getName nos permitirá ir mostrando cada elemento del directorio junto a su nombre
                    // El que este en la misma posicion que la iteracion, por lo que ira desde el 0 hasta X

                    if (contenido[i].isDirectory()) {//isDirectory diferencia/distingue entre archivos y directorios
                        //En caso de que se encuentre un directorio, imprimirá:
                        System.out.println(" <Directorio>");

                        //En caso de que se encuentre un archivo, imprimirá:
                    } else {
                        System.out.println(" " + contenido[i].length() + " bytes");//.length cuando es un archivo nos dice su tamaño (bytes)
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
                // Ir al directorio padre si existe [*6]
                File padre = directorioActual.getParentFile();

                //verificamos que exista un directorio padre y que tenemos permiso de lectura sobre le directorio padre
                if (padre != null && padre.canRead()) {
                    //solo nos permitirá que el directorioActual pase al directorio padre si se cumplen las condiciones
                    directorioActual = padre;

                } //si estamos en la raiz devuelve 'null' ya que no hay directorio padre, en ese caso imprimirá lo siguiente:
                  else if (padre == null) {
                    System.out.println("Ya estás en la raíz del sistema.");
                } else {
                    System.out.println("No tienes permiso para acceder al directorio padre.");
                }
            } else if (opcion > 0 && opcion <= contenido.length) {//si el usuario escoge una opcion dentro del rango permitivo
                File seleccionado = contenido[opcion - 1];
                if (seleccionado.isDirectory()) {
                    if (seleccionado.canRead()) {//verificamos que tenemos permisos de lectura
                        directorioActual = seleccionado;
                    } else {//En caso de que no tengamos permisos sobre el directorio
                        System.out.println("No tienes permiso para acceder a este directorio.");
                    }
                } else {
                    System.out.println("La opción seleccionada es un archivo, no un directorio.");
                }
            } else {//si seleccionamos una opción fuera del límite (es mayor a la longitud de las opciones)
                //si la última opción es 25, ponemos 26 se ejecuta este else
                System.out.println("Opción no válida.");
            }
            
            System.out.println(); // Separador entre iteraciones
            
        } while (true);
        
        scanner.close();
        System.out.println("Programa terminado.");
    }
}


            /*[*5]*/
//Listar contenido y enumerarlo
/*for (int i = 0; i < contenido.length; i++) {
    System.out.print((i + 1) + ".- " + contenido[i].getName());
    if (contenido[i].isDirectory()) {
        System.out.println(" <Directorio>");
    } else {
        System.out.println(" " + contenido[i].length() + " bytes");
    }
}*/
//contenido: Es un array de objetos File que contiene todos los elementos (archivos y/o directorios) del directorio actual.
//[i]: Accede al elemento en la posición i del array. En cada iteración del bucle for, i tomará valores desde 0 hasta contenido.length -1.
//.getName(): Es un método de la clase File que devuelve solo el nombre del archivo o directorio (sin la ruta completa).

            /*[*6]*/
//Bloque de código fundamental para navegar hacia atrás en la estructura de directorios
/*File padre = directorioActual.getParentFile();
  if (padre != null && padre.canRead()) {
    directorioActual = padre;
} else if (padre == null) {
    System.out.println("Ya estás en la raíz del sistema.");
} else {
    System.out.println("No tienes permiso para acceder al directorio padre.");
}*/
/*1. File padre = directorioActual.getParentFile();*/
//getParentFile() es un método de la clase File que devuelve el directorio padre del archivo o directorio actual
//Devuelve un objeto File que representa el directorio padre
//Si directorioActual es /home/usuario/documentos/ || padre será File("/home/usuario")