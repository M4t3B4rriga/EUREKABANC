/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.vista;
import ec.edu.monster.controlador.CuentaController;
import ec.edu.monster.modelo.Cuenta;
import java.util.Scanner;
/**
 *
 * @author sebas
 */
public class MenuVista {
    private final Scanner sc = new Scanner(System.in);
    private final CuentaController controller = new CuentaController();

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ BANCO ---");
            System.out.println("1. Ver saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Transferir");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> verSaldo();
                case 2 -> depositar();
                case 3 -> retirar();
                case 4 -> transferir();
                case 5 -> System.out.println("Gracias por usar el sistema.");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    private void verSaldo() {
        System.out.print("Código de cuenta: ");
        String codigo = sc.nextLine();
        double saldo = controller.verSaldo(codigo);
        if (saldo >= 0)
            System.out.println("Saldo: $" + saldo);
        else
            System.out.println("Cuenta no encontrada o error.");
    }

    private void depositar() {
        System.out.print("Código de cuenta: ");
        String codigo = sc.nextLine();
        System.out.print("Monto: ");
        double monto = sc.nextDouble();
        System.out.println(controller.depositar(new Cuenta(codigo, monto)));
    }

    private void retirar() {
        System.out.print("Código de cuenta: ");
        String codigo = sc.nextLine();
        System.out.print("Monto: ");
        double monto = sc.nextDouble();
        System.out.println(controller.retirar(new Cuenta(codigo, monto)));
    }

    private void transferir() {
        System.out.print("Cuenta origen: ");
        String origen = sc.nextLine();
        System.out.print("Cuenta destino: ");
        String destino = sc.nextLine();
        System.out.print("Monto: ");
        double monto = sc.nextDouble();
        System.out.println(controller.transferir(origen, destino, monto));
    }
}
