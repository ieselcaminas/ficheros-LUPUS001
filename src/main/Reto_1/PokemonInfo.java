package Reto_1;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class PokemonInfo {

    public static void main(String[] args) {
        String pokemon = "ditto";
        String urlString = "https://pokeapi.co/api/v2/pokemon/" + pokemon;

        try {
            // Conexión
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Leer JSON con GSON
            InputStreamReader reader = new InputStreamReader(conn.getInputStream());
            JsonObject jsonObject = new Gson().fromJson(reader, JsonObject.class);

            // Nombre, altura y peso
            String name = jsonObject.get("name").getAsString();
            int height = jsonObject.get("height").getAsInt();
            int weight = jsonObject.get("weight").getAsInt();

            System.out.println("Nombre: " + name);
            System.out.println("Altura: " + height);
            System.out.println("Peso: " + weight);

            // Habilidades
            System.out.println("Habilidades:");
            JsonArray abilities = jsonObject.getAsJsonArray("abilities");
            for (int i = 0; i < abilities.size(); i++) {
                JsonObject ability = abilities.get(i).getAsJsonObject().getAsJsonObject("ability");
                System.out.println("\t" + ability.get("name").getAsString());
            }

            // Versiones
            System.out.println("Versiones:");
            JsonArray gameIndices = jsonObject.getAsJsonArray("game_indices");
            List<String> versions = gameIndices
                .asList()
                .stream()
                .map(el -> el.getAsJsonObject()
                    .getAsJsonObject("version")
                    .get("name").getAsString())
                .distinct()
                .collect(Collectors.toList());

            for (String version : versions) {
                System.out.println("\t" + version);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
