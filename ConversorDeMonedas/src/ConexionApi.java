import com.google.gson.*;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ConexionApi extends Principal{
    public void AlmacenarDatos() {

        Monedas monedas = new Monedas();
        try {
            Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy
                    .UPPER_CAMEL_CASE).setPrettyPrinting().create();

            String direccion = "https://v6.exchangerate-api.com/v6/6e7c84fc08ec79fab9f913a4/latest/USD";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().
                    uri(URI.create(direccion)).build();
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
//            System.out.println(json);

            //convertimos el string traido de la API a formato Json
            String json2 = gson.toJson(json);


            JsonElement jsonElement = JsonParser.parseString(json);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            String conversion = jsonObject.get("conversion_rates").toString();


            JsonElement jsonElement2 = JsonParser.parseString(conversion);
            JsonObject jsonObject2 = jsonElement2.getAsJsonObject();



            monedas.setArg(jsonObject2.get("ARS").getAsDouble());
            monedas.setBrs(jsonObject2.get("BRL").getAsDouble());
            monedas.setUsd(jsonObject2.get("USD").getAsDouble());
            monedas.setHnl(jsonObject2.get("HNL").getAsDouble());
            monedas.setCol(jsonObject2.get("COP").getAsDouble());
            monedas.setMxc(jsonObject2.get("MXN").getAsDouble());
            monedas.setGtm(jsonObject2.get("GTQ").getAsDouble());
            monedas.setCnd(jsonObject2.get("CAD").getAsDouble());

//            arg = jsonObject2.get("ASR").getAsDouble();
//            mx = jsonObject2.get("MXN").getAsDouble();
//            System.out.println("El cambio del Peso Argentino es: "+ monedas.getArg());
//            System.out.println("El cambio del Peso Brasileño es: "+ monedas.getBrs());
//            System.out.println("El cambio del Dolar es: "+ monedas.getUsd());
//            System.out.println("El cambio del Lempira es: "+ monedas.getHnl());
//            System.out.println("El cambio del Peso Colombiano es: "+ monedas.getCol());
//            System.out.println("El cambio del Peso Mexicano es: "+ monedas.getMxc());
//            System.out.println("El cambio del Queztal es: "+ monedas.getGtm());
//            System.out.println("El cambio del Dolar Canadiense es: "+ monedas.getCnd());

        }catch (Exception e){
            System.out.println("Error al llamar los datos de la API" + e.getLocalizedMessage());
        }
    }
}
