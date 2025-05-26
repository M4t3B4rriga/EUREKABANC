/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.view;

import java.util.Scanner;

/**
 *
 * @author sebas
 */
public class LoginVista {
    private final String usuarioValido = "MONSTER";
    private final String claveValida = "MONSTER9";

    public boolean iniciarSesion() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Usuario: ");
        String usuario = sc.nextLine();

        System.out.print("Contraseña: ");
        String clave = sc.nextLine();

        if (usuario.equals(usuarioValido) && clave.equals(claveValida)) {
            System.out.println("¡Login exitoso!");
            return true;
        } else {
            System.out.println("Credenciales inválidas.");
            return false;
        }
    }
}
