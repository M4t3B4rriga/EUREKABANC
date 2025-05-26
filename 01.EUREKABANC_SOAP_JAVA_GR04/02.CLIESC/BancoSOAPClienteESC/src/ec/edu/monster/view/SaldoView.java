package ec.edu.monster.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.border.EmptyBorder;

public class SaldoView extends JFrame{
    // Componentes
    public JTextField txtCuenta = new JTextField(10);
    public JLabel lblSaldo = new JLabel("$ 0.00", SwingConstants.RIGHT);
    public JButton btnConsultar = new JButton("Consultar Saldo");
    public JButton btnLimpiar = new JButton("Limpiar");

    public SaldoView() {
        setTitle("Consulta de Saldo");
        setSize(350, 200);
        setLayout(new GridLayout(4, 2, 10, 10));
        
        // Diseño mejorado
        add(new JLabel("Número de Cuenta:"));
        add(txtCuenta);
        add(new JLabel("Saldo Actual:"));
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 14));
        lblSaldo.setForeground(Color.BLUE);
        add(lblSaldo);
        
        // Botones con estilo
        btnConsultar.setBackground(new Color(70, 130, 180));
        btnConsultar.setForeground(Color.WHITE);
        add(btnConsultar);
        
        btnLimpiar.setBackground(new Color(220, 20, 60));
        btnLimpiar.setForeground(Color.WHITE);
        add(btnLimpiar);
        
        // Acción para limpiar campos
        btnLimpiar.addActionListener(e -> {
            txtCuenta.setText("");
            lblSaldo.setText("$ 0.00");
        });
    }
}
