package ec.edu.monster.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

public class RetiroView extends JFrame{
    public JFormattedTextField txtMonto;
    public JTextField txtCuenta;
    public JButton btnConfirmar, btnCancelar;

    public RetiroView() {
        setTitle("Retiro - MonsterBank");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel con fondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon(getClass().getResource("/recursos/Retiro.png"));
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Componentes
        JLabel lblTitulo = new JLabel("RETIRO BANCARIO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        // Formateador para monto numérico
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setMaximum(10000.0);
        txtMonto = new JFormattedTextField(formatter);
        txtMonto.setColumns(15);
        txtMonto.setHorizontalAlignment(JTextField.RIGHT);

        txtCuenta = new JTextField(15);
        JLabel lblCuenta = new JLabel("Cuenta de origen:");
        JLabel lblMonto = new JLabel("Monto a retirar:");
        lblCuenta.setForeground(Color.WHITE);
        lblMonto.setForeground(Color.WHITE);

        btnConfirmar = new JButton("Confirmar Retiro");
        btnCancelar = new JButton("Cancelar");
        styleButton(btnConfirmar, new Color(200, 50, 50)); // Rojo bancario
        styleButton(btnCancelar, new Color(100, 100, 100));

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        formPanel.add(lblCuenta, gbc);
        gbc.gridy++;
        formPanel.add(txtCuenta, gbc);
        gbc.gridy++;
        formPanel.add(lblMonto, gbc);
        gbc.gridy++;
        formPanel.add(txtMonto, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(btnConfirmar);
        buttonPanel.add(btnCancelar);

        // Layout principal
        mainPanel.add(lblTitulo, new GridBagConstraints(0, 0, 1, 1, 1.0, 0.1, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 20, 0), 0, 0));
        mainPanel.add(formPanel, new GridBagConstraints(0, 1, 1, 1, 1.0, 0.7, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        mainPanel.add(buttonPanel, new GridBagConstraints(0, 2, 1, 1, 1.0, 0.2, 
                GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(20, 0, 0, 0), 0, 0));

        // Listeners
        btnCancelar.addActionListener(e -> dispose());
        btnConfirmar.addActionListener(this::procesarRetiro);

        add(mainPanel);
        setVisible(true);
    }

    private void procesarRetiro(ActionEvent e) {
        try {
            double monto = (txtMonto.getValue() != null) ? ((Number)txtMonto.getValue()).doubleValue() : 0;
            String cuenta = txtCuenta.getText().trim();
            
            if (cuenta.isEmpty() || monto <= 0) {
                throw new IllegalArgumentException();
            }
            
            // Simulación de retiro exitoso
            JOptionPane.showMessageDialog(this, 
                "Retiro exitoso de $" + String.format("%,.2f", monto) + "\nCuenta: " + cuenta,
                "Operación Completada", 
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Datos inválidos\n- Cuenta no puede estar vacía\n- Monto debe ser positivo", 
                "Error en Retiro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.darker());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
    }
}
