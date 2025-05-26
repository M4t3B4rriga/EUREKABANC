/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.service;
import ec.edu.monster.ws.BancoWS;
import ec.edu.monster.ws.BancoWSService;
/**
 *
 * @author sebas
 */
public class BancoService {
    private final BancoWS servicio;
    
    public BancoService() {
        BancoWSService servicioWS = new BancoWSService();
        this.servicio = servicioWS.getBancoWSPort();
    }
    
    public String depositar(String cuenta, double monto) {
        return servicio.depositar(cuenta, monto);
    }
    
    public String retirar(String cuenta, double monto) {
        return servicio.retirar(cuenta, monto);
    }
    
    public String transferir(String origen, String destino, double monto) {
        return servicio.transferir(origen, destino, monto);
    }
    
    public double consultarSaldo(String cuenta) {
        return servicio.verSaldo(cuenta);
    }
}
