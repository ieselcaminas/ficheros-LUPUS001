package GSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EmpleadoToPrettyPrintingJSON_3 {
    public static void main(String[] args) {
        final Empleado empleado = new Empleado(46, "Miguel", 10, 1100,00);
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        final String prettyPrinting = prettyGson.toJson(empleado);
        System.out.println(prettyPrinting);
    }
}
