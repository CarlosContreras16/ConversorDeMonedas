import com.google.gson.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
import  java.time.format.DateTimeFormatter;

public class Principal{
    public static void main(String[] args) throws IOException, InterruptedException {
        ConexionApi conexionApi = new ConexionApi();
        List<Bitacora> conversiones = new ArrayList<>();
//        conexionApi.AlmacenarDatos();
        Monedas monedas = new Monedas();
        Scanner lectura = new Scanner(System.in);
        String mensajefinal = "";
        Double total;
        String busqueda2;
        DateTimeFormatter fecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hora = DateTimeFormatter.ofPattern("HH:mm:ss");

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
            monedas.setEur(jsonObject2.get("EUR").getAsDouble());

        }catch (Exception e){
            System.out.println("Error al llamar los datos de la API" + e.getLocalizedMessage());
        }

        while (true){

            System.out.println("*****************************************");
            System.out.println("Sea Bienvenido/a al conversor de monedas =)");
            System.out.println("\n1) Dolar =>> Peso Argentino");
            System.out.println("2) Peso Argentino =>> Dolar");
            System.out.println("3) Dólar =>> Real Brasileño");
            System.out.println("4) Real Brasileño =>> Dólar");
            System.out.println("5) Dólar =>> Peso Colombiano");
            System.out.println("6) Peso Colombiano =>> Dólar");
            System.out.println("7) Dolar =>> Lempira");
            System.out.println("8) Lempira =>> Dolar");
            System.out.println("9) Dolar =>> Quetzal");
            System.out.println("10) Quetzal =>> Dolar");
            System.out.println("11) Dolar =>> Dolar Canadiense");
            System.out.println("12) Dolar Canandiense =>> Dolar");
            System.out.println("13) Dolar =>> Euro");
            System.out.println("14) Euro =>> Dolar");
            System.out.println("15) Salir");
            System.out.println("Elija una opcion válida:");
            System.out.println("\n*****************************************");
            var busqueda = lectura.nextLine();
            if (busqueda.equalsIgnoreCase("1")){
                System.out.println("Ingrese el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) * monedas.getArg();
                mensajefinal = busqueda2 + " dolares equivalen a "+ String.format("%.2f", total) +" Pesos Argentinos";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares a Pesos Argentinos y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("2")){
                System.out.println("Ingrese el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) / monedas.getArg() ;
                mensajefinal = busqueda2 + " Pesos Argentinos equivalen a "+ String.format("%.2f", total) +" Dolares Estadounidenses";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Pesos Argentinos a Dolares y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("3")){
                System.out.println("Ingrese el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) * monedas.getBrs();
                mensajefinal = busqueda2 + " Dolares equivalen a "+ String.format("%.2f", total) +" Reales Brasileños";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares a Reales Brasileños y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("4")){
                System.out.println("Ingrese el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) / monedas.getBrs() ;
                mensajefinal = busqueda2 + " Reales Brasileños equivalen a "+ String.format("%.2f", total) +" Dolares Estadounidenses";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Reales Brasileños a Dolares y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("5")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) * monedas.getCol();
                mensajefinal = busqueda2 + " dolares equivalen a "+ String.format("%.2f", total) +" Pesos Colombianos";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares a Pesos Colombianos y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("6")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) / monedas.getCol() ;
                mensajefinal = busqueda2 + " Pesos Colombianos equivalen a "+ String.format("%.2f", total) +" Dolares Estadounidenses";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Pesos Colombianos a Dolares y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("7")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) * monedas.getHnl();
                mensajefinal = busqueda2 + " dolares equivalen a "+ String.format("%.2f", total) +" Lempiras";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares a Lempiras y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("8")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) / monedas.getHnl();
                mensajefinal = busqueda2 + " Lempiras equivalen a "+ String.format("%.2f", total) +" Dolares Estadounidenses";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Lempiras a Dolares y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("9")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) * monedas.getGtm();
                mensajefinal = busqueda2 + " dolares equivalen a "+ String.format("%.2f", total) +" Quetzales";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares a Quetzales y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("10")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) / monedas.getGtm();
                mensajefinal = busqueda2 + " Quetzales equivalen a "+ String.format("%.2f", total) +" Dolares Estadounidenses";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Quetzales a Dolares y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("11")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) * monedas.getCnd();
                mensajefinal = busqueda2 + " dolares equivalen a "+ String.format("%.2f", total) +" Dolares Canadienses";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares a Dolares Canadienses y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("12")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) / monedas.getCnd();
                mensajefinal = busqueda2 + " Dolares Canandienes equivalen a "+ String.format("%.2f", total) +" Dolares Estadounidenses";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares Canadienses a Dolares y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("13")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) * monedas.getEur();
                mensajefinal = busqueda2 + " Dolares equivalen a "+ String.format("%.2f", total) +" Euros";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Dolares a Euros y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if (busqueda.equalsIgnoreCase("14")){
                System.out.println("Ingreses el valor que deseas convertir: ");
                busqueda2 = lectura.nextLine();
                total = Double.parseDouble(busqueda2) / monedas.getEur();
                mensajefinal = busqueda2 + " Euros equivalen a "+ String.format("%.2f", total) +" Dolares";
                MisConversiones misConversiones = new MisConversiones("Se convitirtieron " + busqueda2 + " Euros a Dolares y su total fue: " + String.format("%.2f", total) + " El " + fecha.format(LocalDate.now()) + " a las " + hora.format(LocalTime.now()));
                Bitacora bitacora = new Bitacora(misConversiones);
                conversiones.add(bitacora);
            }
            if(busqueda.equalsIgnoreCase("15")){
                break;
            }
            else
            {
                System.out.println("Debe ingresar un numero valido");
            }
            System.out.println(mensajefinal);
            total = 0.0;
        }
        System.out.println(conversiones);
//        System.out.println("El valor del peso Argentino al dia de hoy es: " + monedas.arg);
//        System.out.println("El valor del peso Mexicano al dia de hoy es: " + monedas.getMxc());

    }
}
