import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Introduce un directorio:");

        String ent = nombreFichero(); //llamamos al metodo para saber el nombre del archivo

        File f = new File(ent);
        try {
            imprimirDirectorio(f);
        } catch (IOException e) {
            System.out.println("No existe el directorio");
        }
    }

    private static String nombreFichero() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    private static void imprimirFichero(File e){
        if (!e.isHidden()) {
            if (e.isFile()) {
                System.out.println(e.getName() + " " + e.length());
            }
            if (e.isDirectory()){
                System.out.println(e.getName() + " <Directorio>");
            }
        }
    }

    private static void imprimirDirectorio(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                System.out.println("Lista de ficheros y directorios del directorio: " + file.getCanonicalPath());
                System.out.println("-----------------------------------------------------");

                for (File e : file.listFiles()){
                    imprimirFichero(e);
                }
            } else {
                System.out.println("NO es un directorio");
            }
        } else {
            System.out.println("NO EXISTE el directorio, aceptalo");
        }
    }
}
