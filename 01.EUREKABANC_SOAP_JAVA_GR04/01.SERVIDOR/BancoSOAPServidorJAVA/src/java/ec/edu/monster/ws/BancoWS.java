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
    private final ClienteService clienteController = new ClienteService();
    private final CuentaService cuentaController = new CuentaService();
    private final EmpleadoService empleadoController = new EmpleadoService();
    private final MovimientoService movimientoController = new MovimientoService();
    
     @WebMethod
    public Object getClientes() {
        return clienteController.listarClientes();
    }

    @WebMethod
    public Object getCuentas() {
        return cuentaController.listarCuentas();
    }

    @WebMethod
    public Object getEmpleados() {
        return empleadoController.listarEmpleados();
    }

    @WebMethod
    public Object getMovimientos() {
        return movimientoController.listarMovimientos();
    }
}
