/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.controller;
import ec.edu.monster.service.BancoService;
import java.util.Scanner;
/**
 *
 * @author sebas
 */
public class BancoController {
    private final BancoService bancoService = new BancoService();
    
    public void depositar(Scanner scanner){
        System.out.println("\n ----- DEPOSITO -----");
        System.out.println("Ingresar numero de cuenta: ");
        String cuenta= scanner.nextLine();
        
        System.out.println("Ingresar monto: ");
        double monto= scanner.nextDouble();
        
        String resultado = bancoService.depositar(cuenta, monto);
        System.out.println("Resultado"+resultado);
    }
    public void retirar(Scanner scanner) {
        System.out.println("\n=== RETIRO ===");
        System.out.print("Ingrese número de cuenta: ");
        String cuenta = scanner.nextLine();
        
        System.out.print("Ingrese monto a retirar: ");
        double monto = scanner.nextDouble();
        
        String resultado = bancoService.retirar(cuenta, monto);
        System.out.println("Resultado: " + resultado);
    }
    
    public void transferir(Scanner scanner) {
        System.out.println("\n=== TRANSFERENCIA ===");
        System.out.print("Ingrese cuenta origen: ");
        String origen = scanner.nextLine();
        
        System.out.print("Ingrese cuenta destino: ");
        String destino = scanner.nextLine();
        
        System.out.print("Ingrese monto a transferir: ");
        double monto = scanner.nextDouble();
        
        String resultado = bancoService.transferir(origen, destino, monto);
        System.out.println("Resultado: " + resultado);
    }
    
    public void consultarSaldo(Scanner scanner) {
        System.out.println("\n=== CONSULTA DE SALDO ===");
        System.out.print("Ingrese número de cuenta: ");
        String cuenta = scanner.nextLine();
        
        double saldo = bancoService.consultarSaldo(cuenta);
        if(saldo >= 0) {
            System.out.println("Saldo actual: " + saldo);
        } else {
            System.out.println("Error al consultar saldo");
        }
    }
    
}
