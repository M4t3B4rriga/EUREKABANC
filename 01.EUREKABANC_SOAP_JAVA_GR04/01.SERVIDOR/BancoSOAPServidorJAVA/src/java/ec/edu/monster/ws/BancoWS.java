/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.ws;
import ec.edu.monster.controller.*;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
/**
 *
 * @author sebas
 */


@WebService
public class BancoWS {
    private final CuentaService cuentaController = new CuentaService();
    private final MovimientoService movimientoController = new MovimientoService();
    


    @WebMethod
public String depositar(String cuentaCodigo, double monto) {
    return cuentaController.depositar(cuentaCodigo, monto);
}

@WebMethod
public String retirar(String cuentaCodigo, double monto) {
    return cuentaController.retirar(cuentaCodigo, monto);
}

@WebMethod
public String transferir(String origen, String destino, double monto) {
    return cuentaController.transferir(origen, destino, monto);
}

@WebMethod
public double verSaldo(String cuentaCodigo) {
    return cuentaController.verSaldo(cuentaCodigo);
}
}
