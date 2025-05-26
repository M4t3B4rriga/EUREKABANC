package ec.edu.monster.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;

public class TransferenciaView extends JFrame{
    public JFormattedTextField txtMonto;
    public JTextField txtCuentaOrigen, txtCuentaDestino;
    public JButton btnTransferir, btnLimpiar, btnCancelar;

    public TransferenciaView() {
        setTitle("Transferencia Bancaria - MonsterBank");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Panel con fondo degradado
        JPanel mainPanel = new JPanel() {
            @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // 1. Carga la imagen desde tus recursos
        ImageIcon imagenFondo = new ImageIcon(getClass().getResource("/recursos/Transferencia.png"));
        // 2. Dibuja la imagen ajustada al tamaño del panel
        g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
    }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        // Panel de título
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        JLabel lblTitulo = new JLabel("TRANSFERENCIA ELECTRÓNICA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        titlePanel.add(lblTitulo);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Configuración del campo de monto
        NumberFormat format = NumberFormat.getNumberInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.01);
        formatter.setMaximum(10000.00);
        txtMonto = new JFormattedTextField(formatter);
        txtMonto.setColumns(15);
        txtMonto.setHorizontalAlignment(JTextField.RIGHT);

        // Componentes del formulario
        txtCuentaOrigen = new JTextField(15);
        txtCuentaDestino = new JTextField(15);

        JLabel lblCuentaOrigen = createLabel("Cuenta origen:");
        JLabel lblCuentaDestino = createLabel("Cuenta destino:");
        JLabel lblMonto = createLabel("Monto a transferir:");

        // Posicionamiento de componentes
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblCuentaOrigen, gbc);

        gbc.gridy = 1;
        formPanel.add(txtCuentaOrigen, gbc);

        gbc.gridy = 2;
        formPanel.add(lblCuentaDestino, gbc);

        gbc.gridy = 3;
        formPanel.add(txtCuentaDestino, gbc);

        gbc.gridy = 4;
        formPanel.add(lblMonto, gbc);

        gbc.gridy = 5;
        formPanel.add(txtMonto, gbc);

        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        btnTransferir = createButton("Transferir", new Color(40, 180, 70));
        btnLimpiar = createButton("Limpiar", new Color(240, 180, 30));
        btnCancelar = createButton("Cancelar", new Color(200, 60, 60));

        buttonPanel.add(btnTransferir);
        buttonPanel.add(btnLimpiar);
        buttonPanel.add(btnCancelar);

        // Ensamblado final
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Listeners
        btnCancelar.addActionListener(e -> dispose());
        btnLimpiar.addActionListener(e -> limpiarFormulario());
        btnTransferir.addActionListener(this::procesarTransferencia);

        add(mainPanel);
        setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setForeground(new Color(220, 220, 220));
        return label;
    }

    private JButton createButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(color.darker(), 2),
            BorderFactory.createEmptyBorder(8, 20, 8, 20)
        ));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    private void limpiarFormulario() {
        txtCuentaOrigen.setText("");
        txtCuentaDestino.setText("");
        txtMonto.setValue(null);
    }

    private void procesarTransferencia(ActionEvent e) {
        try {
            // Validaciones
            String cuentaOrigen = txtCuentaOrigen.getText().trim();
            String cuentaDestino = txtCuentaDestino.getText().trim();
            double monto = (txtMonto.getValue() != null) ? ((Number)txtMonto.getValue()).doubleValue() : 0;

            if (cuentaOrigen.isEmpty() || cuentaDestino.isEmpty()) {
                throw new IllegalArgumentException("Las cuentas no pueden estar vacías");
            }

            if (cuentaOrigen.equals(cuentaDestino)) {
                throw new IllegalArgumentException("No puede transferir a la misma cuenta");
            }

            if (monto <= 0) {
                throw new IllegalArgumentException("Monto debe ser mayor a cero");
            }

            // Simulación de transferencia exitosa
            String mensaje = String.format(
                "<html><center><b>¡Transferencia Exitosa!</b><br>" +
                "<font size=4>De: %s<br>A: %s<br>Monto: $%,.2f</font></center></html>",
                cuentaOrigen, cuentaDestino, monto
            );

            dispose();

        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, 
                "<html><b>Error en transferencia:</b><br>" + ex.getMessage() + "</html>", 
                "Validación", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
}
