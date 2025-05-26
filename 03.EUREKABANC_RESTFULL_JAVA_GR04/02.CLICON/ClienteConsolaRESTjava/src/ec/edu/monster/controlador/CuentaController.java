/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controlador;
import ec.edu.monster.modelo.Cuenta;
import java.io.*;
import java.net.*;
import com.google.gson.Gson;
/**
 *
 * @author sebas
 */
public class CuentaController {
   private final String baseUrl = "http://localhost:8080/BancoRESTServidorJAVA/api/cuenta";
    private final Gson gson = new Gson();

    public double verSaldo(String codigo) {
        try {
            URL url = new URL(baseUrl + "/saldo/" + codigo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Cuenta cuenta = gson.fromJson(br, Cuenta.class);
            return cuenta.getSaldo();
        } catch (Exception e) {
            System.out.println("Error al obtener saldo: " + e.getMessage());
            return -1;
        }
    }

    public String depositar(Cuenta cuenta) {
        return enviarOperacion("/depositar", cuenta);
    }

    public String retirar(Cuenta cuenta) {
        return enviarOperacion("/retirar", cuenta);
    }

    public String transferir(String origen, String destino, double monto) {
        try {
            String query = String.format("?origen=%s&destino=%s&monto=%s", origen, destino, monto);
            URL url = new URL(baseUrl + "/transferir" + query);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.getOutputStream().write("{}".getBytes()); // cuerpo vacío

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            return br.readLine();
        } catch (Exception e) {
            return "Error al transferir: " + e.getMessage();
        }
    }

    private String enviarOperacion(String path, Cuenta cuenta) {
        try {
            URL url = new URL(baseUrl + path);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");

            String json = gson.toJson(cuenta);
            try (OutputStream os = con.getOutputStream()) {
                os.write(json.getBytes());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            return br.readLine();
        } catch (Exception e) {
            return "Error en operación: " + e.getMessage();
        }
    }
}
