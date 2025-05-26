/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.vista;
import java.util.Scanner;
/**
 *
 * @author sebas
 */
public class LoginVista {
     private final String USUARIO = "MONSTER";
    private final String CLAVE = "MONSTER9";

    public boolean iniciarSesion() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String clave = sc.nextLine();

        if (usuario.equals(USUARIO) && clave.equals(CLAVE)) {
            System.out.println("¡Inicio de sesión exitoso!");
            return true;
        } else {
            System.out.println("Credenciales inválidas.");
            return false;
        }
    }
}
