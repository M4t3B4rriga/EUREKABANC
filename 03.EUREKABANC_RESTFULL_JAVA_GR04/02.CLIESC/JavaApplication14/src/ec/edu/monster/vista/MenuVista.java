/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.vista;

import javax.swing.*;
import java.awt.*;
/**
 *
 * @author sebas
 */
public class MenuVista extends JFrame{
    public MenuVista() {
        setTitle("Menú Principal");
        setSize(400, 400);
        setContentPane(new JLabel(new ImageIcon("recursos/Menu.png")));
        setLayout(new GridLayout(5, 1));

        JButton verSaldo = new JButton("Ver Saldo");
        JButton deposito = new JButton("Depositar");
        JButton retiro = new JButton("Retirar");
        JButton transferencia = new JButton("Transferir");
        JButton salir = new JButton("Salir");

        add(verSaldo);
        add(deposito);
        add(retiro);
        add(transferencia);
        add(salir);

        verSaldo.addActionListener(e -> new SaldoVista());
        deposito.addActionListener(e -> new DepositoVista());
        retiro.addActionListener(e -> new RetiroVista());
        transferencia.addActionListener(e -> new TransferenciaVista());
        salir.addActionListener(e -> System.exit(0));

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
