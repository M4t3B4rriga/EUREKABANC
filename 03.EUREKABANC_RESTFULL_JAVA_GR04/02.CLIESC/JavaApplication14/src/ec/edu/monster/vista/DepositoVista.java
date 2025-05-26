/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.monster.vista;

import ec.edu.monster.controlador.CuentaController;
import ec.edu.monster.modelo.Cuenta;
import javax.swing.*;
/**
 *
 * @author sebas
 */
public class DepositoVista extends JFrame{
    public DepositoVista() {
        setTitle("Depositar");
        setSize(400, 300);
        setContentPane(new JLabel(new ImageIcon("recursos/Deposito.png")));
        setLayout(null);

        JLabel lblCuenta = new JLabel("Cuenta:");
        JLabel lblMonto = new JLabel("Monto:");
        JTextField txtCuenta = new JTextField();
        JTextField txtMonto = new JTextField();
        JButton btn = new JButton("Depositar");

        lblCuenta.setBounds(100, 80, 80, 25);
        txtCuenta.setBounds(180, 80, 100, 25);
        lblMonto.setBounds(100, 120, 80, 25);
        txtMonto.setBounds(180, 120, 100, 25);
        btn.setBounds(150, 170, 100, 30);

        add(lblCuenta); add(txtCuenta);
        add(lblMonto); add(txtMonto);
        add(btn);

        btn.addActionListener(e -> {
            String cuenta = txtCuenta.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            CuentaController controller = new CuentaController();
            String respuesta = controller.depositar(new Cuenta(cuenta, monto));
            JOptionPane.showMessageDialog(this, respuesta);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
