/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controlador;
import com.google.gson.Gson;
import ec.edu.monster.modelo.Cuenta;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 *
 * @author sebas
 */
public class CuentaController {
    private final String BASE_URL = "http://192.168.0.102:8080/BancoRESTServidorJAVA/api/cuenta";
    private final Gson gson = new Gson();

    public double verSaldo(String codigo) {
        try {
            URL url = new URL(BASE_URL + "/saldo/" + codigo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            Cuenta cuenta = gson.fromJson(br, Cuenta.class);
            return cuenta.getSaldo();
        } catch (Exception e) {
            return -1;
        }
    }

    public String depositar(Cuenta cuenta) {
        return postCuenta("/depositar", cuenta);
    }

    public String retirar(Cuenta cuenta) {
        return postCuenta("/retirar", cuenta);
    }

    public String transferir(String origen, String destino, double monto) {
        try {
            String query = String.format("?origen=%s&destino=%s&monto=%s", origen, destino, monto);
            URL url = new URL(BASE_URL + "/transferir" + query);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.getOutputStream().write("{}".getBytes());

            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            return br.readLine();
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    private String postCuenta(String path, Cuenta cuenta) {
        try {
            URL url = new URL(BASE_URL + path);
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
            return "Error: " + e.getMessage();
        }
    }
}
