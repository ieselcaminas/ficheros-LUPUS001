package Reto_2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class ApiPokemon2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Introduce el nombre de un Pokémon (o vacío para salir): ");
            String nombre = scanner.nextLine().trim().toLowerCase();

            if (nombre.isEmpty()) {
                System.out.println("Programa finalizado.");
                break;
            }

            try {
                String url = "https://pokeapi.co/api/v2/pokemon/" + nombre;
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");

                if (conn.getResponseCode() == 200) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder content = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    conn.disconnect();

                    JSONObject json = new JSONObject(content.toString());

                    int id = json.getInt("id");
                    int height = json.getInt("height");
                    int weight = json.getInt("weight");

                    System.out.println("ID: " + id);
                    System.out.println("Altura: " + height);
                    System.out.println("Peso: " + weight);

                    System.out.print("Tipos: ");
                    JSONArray types = json.getJSONArray("types");
                    for (int i = 0; i < types.length(); i++) {
                        JSONObject type = types.getJSONObject(i).getJSONObject("type");
                        System.out.print(type.getString("name") + (i < types.length() - 1 ? ", " : "\n"));
                    }

                    System.out.print("Habilidades: ");
                    JSONArray abilities = json.getJSONArray("abilities");
                    for (int i = 0; i < abilities.length(); i++) {
                        JSONObject ability = abilities.getJSONObject(i).getJSONObject("ability");
                        System.out.print(ability.getString("name") + (i < abilities.length() - 1 ? ", " : "\n"));
                    }

                    System.out.println("Estadísticas:");
                    JSONArray stats = json.getJSONArray("stats");
                    for (int i = 0; i < stats.length(); i++) {
                        JSONObject stat = stats.getJSONObject(i);
                        String statName = stat.getJSONObject("stat").getString("name");
                        int baseStat = stat.getInt("base_stat");
                        System.out.println(" - " + statName + ": " + baseStat);
                    }

                } else {
                    System.out.println("Pokémon no encontrado.");
                }

            } catch (Exception e) {
                System.out.println("Error al recuperar los datos del Pokémon.");
            }
        }

        scanner.close();
    }
}
