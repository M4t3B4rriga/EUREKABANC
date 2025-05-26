package ec.edu.monster.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class DepositoView extends JFrame{
    public JTextField txtCuenta, txtMonto;
    public JButton btnConfirmar, btnCancelar;

    public DepositoView() {
        setTitle("Depósito - MonsterBank");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Panel con fondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon(getClass().getResource("/ec/edu/monster/img/Depositos.png"));
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Componentes
        JLabel lblTitulo = new JLabel("DEPÓSITO", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);

        txtCuenta = new JTextField(15);
        txtMonto = new JTextField(15);
        JLabel lblCuenta = new JLabel("Número de Cuenta:");
        JLabel lblMonto = new JLabel("Monto a Depositar:");
        lblCuenta.setForeground(Color.WHITE);
        lblMonto.setForeground(Color.WHITE);

        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");
        styleButton(btnConfirmar, new Color(46, 125, 50));
        styleButton(btnCancelar, new Color(198, 40, 40));

        // Posicionamiento
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;

        gbc.gridy = 0;
        mainPanel.add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        mainPanel.add(lblCuenta, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtCuenta, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lblMonto, gbc);
        gbc.gridx = 1;
        mainPanel.add(txtMonto, gbc);

        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(Box.createVerticalStrut(20), gbc);

        gbc.gridy = 4;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        btnPanel.setOpaque(false);
        btnPanel.add(btnConfirmar);
        btnPanel.add(btnCancelar);
        mainPanel.add(btnPanel, gbc);

        // Listeners
        btnCancelar.addActionListener(e -> dispose());
        btnConfirmar.addActionListener(this::validarDeposito);

        add(mainPanel);
        setVisible(true);
    }

    private void validarDeposito(ActionEvent e) {
        try {
            double monto = Double.parseDouble(txtMonto.getText());
            if (monto <= 0) throw new NumberFormatException();
            JOptionPane.showMessageDialog(this, "Depósito exitoso a cuenta: " + txtCuenta.getText());
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un monto válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void styleButton(JButton button, Color color) {
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }
}
