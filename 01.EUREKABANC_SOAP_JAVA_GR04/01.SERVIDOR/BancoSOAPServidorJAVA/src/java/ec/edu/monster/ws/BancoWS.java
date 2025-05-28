package ec.edu.monster.ws;

<<<<<<< HEAD
import java.util.List;
import ec.edu.monster.controller.CuentaService;
import ec.edu.monster.controller.MovimientoService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import ec.edu.monster.model.Movimiento;

/**
 *
 * @author sebas
 */
=======
import ec.edu.monster.controller.*;
import ec.edu.monster.model.Movimiento;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.List;
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1

@WebService
public class BancoWS {

    private final CuentaService cuentaController = new CuentaService();
    private final MovimientoService movimientoController = new MovimientoService();
<<<<<<< HEAD

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

=======
    
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
    
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
    @WebMethod
    public List<Movimiento> consultarMovimientos(String cuentaCodigo) {
        return movimientoController.listarMovimientosPorCuenta(cuentaCodigo);
    }
<<<<<<< HEAD

}
=======
}
>>>>>>> f314e0f09a32b7739f700b8e03fe9c5690fb3ec1
