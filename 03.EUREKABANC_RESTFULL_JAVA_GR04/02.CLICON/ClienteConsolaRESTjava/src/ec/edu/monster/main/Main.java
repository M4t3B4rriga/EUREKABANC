/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.main;
import ec.edu.monster.vista.LoginVista;
import ec.edu.monster.vista.MenuVista;
/**
 *
 * @author sebas
 */
public class Main {
    public static void main(String[] args) {
        LoginVista login = new LoginVista();
        if (login.iniciarSesion()) {
            new MenuVista().mostrarMenu();
        } else {
            System.out.println("Saliendo del sistema.");
        }
    }
}
