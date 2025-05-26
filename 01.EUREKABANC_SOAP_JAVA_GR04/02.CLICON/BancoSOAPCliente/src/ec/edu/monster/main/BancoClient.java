/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.main;
import ec.edu.monster.controller.BancoController;
import java.util.Scanner;
/**
 *
 * @author sebas
 */
public class BancoClient {
    private static final String USUARIO_VALIDO = "MONSTER";
    private static final String CLAVE_VALIDA = "MONSTER9";
    private static final int INTENTOS_MAXIMOS = 3;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        if (!autenticarUsuario(scanner)) {
            System.out.println("\n[ERROR] Demasiados intentos fallidos. Sistema bloqueado.");
            System.exit(1);
        }
        
        // Si la autenticación fue exitosa
        iniciarSistemaBancario(scanner);
    }
    
    private static boolean autenticarUsuario(Scanner scanner) {
        int intentos = 0;
        
        System.out.println("=== AUTENTICACIÓN ===");
        
        while (intentos < INTENTOS_MAXIMOS) {
            System.out.print("\nUsuario: ");
            String usuario = scanner.nextLine();
            
            System.out.print("Contraseña: ");
            String clave = scanner.nextLine();
            
            if (usuario.equals(USUARIO_VALIDO) && clave.equals(CLAVE_VALIDA)) {
                System.out.println("\n¡Autenticación exitosa!");
                return true;
            } else {
                intentos++;
                System.out.println("\nCredenciales incorrectas. Intentos restantes: " + (INTENTOS_MAXIMOS - intentos));
            }
        }
        return false;
    }
    
    private static void iniciarSistemaBancario(Scanner scanner) {
        BancoController controller = new BancoController();
        
        while(true) {
            System.out.println("\n=== SISTEMA BANCARIO ===");
            System.out.println("1. Depositar");
            System.out.println("2. Retirar");
            System.out.println("3. Transferir");
            System.out.println("4. Consultar saldo");
            System.out.println("5. Salir");
            System.out.print("Seleccione opción: ");
            
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer
            
            switch(opcion) {
                case 1:
                    controller.depositar(scanner);
                    break;
                case 2:
                    controller.retirar(scanner);
                    break;
                case 3:
                    controller.transferir(scanner);
                    break;
                case 4:
                    controller.consultarSaldo(scanner);
                    break;
                case 5:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida");
            }
        }
    }
}
