/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.vista;
import ec.edu.monster.controlador.CuentaController;

import javax.swing.*;
/**
 *
 * @author sebas
 */
public class SaldoVista extends JFrame{
    public SaldoVista() {
        setTitle("Ver Saldo");
        setSize(400, 300);
        setContentPane(new JLabel(new ImageIcon("recursos/ConsultaSaldo.png")));
        setLayout(null);

        JLabel lbl = new JLabel("Código Cuenta:");
        JTextField txt = new JTextField();
        JButton btn = new JButton("Consultar");

        lbl.setBounds(100, 100, 100, 25);
        txt.setBounds(200, 100, 100, 25);
        btn.setBounds(150, 150, 100, 30);

        add(lbl); add(txt); add(btn);

        btn.addActionListener(e -> {
            String codigo = txt.getText();
            double saldo = new CuentaController().verSaldo(codigo);
            JOptionPane.showMessageDialog(this, "Saldo: $" + saldo);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
