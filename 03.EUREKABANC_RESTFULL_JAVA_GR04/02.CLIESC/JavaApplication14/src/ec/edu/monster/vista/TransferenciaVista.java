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
public class TransferenciaVista extends JFrame{
    public TransferenciaVista() {
        setTitle("Transferir");
        setSize(400, 350);
        setContentPane(new JLabel(new ImageIcon("recursos/Transferencia.png")));
        setLayout(null);

        JLabel lblOrigen = new JLabel("Cuenta Origen:");
        JLabel lblDestino = new JLabel("Cuenta Destino:");
        JLabel lblMonto = new JLabel("Monto:");
        JTextField txtOrigen = new JTextField();
        JTextField txtDestino = new JTextField();
        JTextField txtMonto = new JTextField();
        JButton btn = new JButton("Transferir");

        lblOrigen.setBounds(100, 70, 100, 25);
        txtOrigen.setBounds(200, 70, 120, 25);
        lblDestino.setBounds(100, 110, 100, 25);
        txtDestino.setBounds(200, 110, 120, 25);
        lblMonto.setBounds(100, 150, 100, 25);
        txtMonto.setBounds(200, 150, 120, 25);
        btn.setBounds(150, 200, 100, 30);

        add(lblOrigen); add(txtOrigen);
        add(lblDestino); add(txtDestino);
        add(lblMonto); add(txtMonto);
        add(btn);

        btn.addActionListener(e -> {
            String origen = txtOrigen.getText();
            String destino = txtDestino.getText();
            double monto = Double.parseDouble(txtMonto.getText());
            CuentaController controller = new CuentaController();
            String respuesta = controller.transferir(origen, destino, monto);
            JOptionPane.showMessageDialog(this, respuesta);
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
