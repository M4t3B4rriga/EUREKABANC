package ec.edu.monster.service;
import ec.edu.monster.ws.BancoWSService;
import ec.edu.monster.ws.BancoWS;
public class BancoSOAPService {
    private final BancoWS servicio;

    public BancoSOAPService() {
        BancoWSService service = new BancoWSService();
        this.servicio = service.getBancoWSPort();
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

    public double verSaldo(String cuenta) {
        return servicio.verSaldo(cuenta);
    }
}
