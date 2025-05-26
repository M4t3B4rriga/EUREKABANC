package ec.edu.monster.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BancoView extends JFrame{
   public JButton btnDepositar, btnRetirar, btnTransferir, btnSaldo;

    public BancoView() {
        setTitle("MonsterBank - Menú Principal");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel con imagen de fondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon bg = new ImageIcon(getClass().getResource("/recursos/Menu.png"));
                g.drawImage(bg.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new GridBagLayout());

        // Estilo de botones
        Font buttonFont = new Font("Arial", Font.BOLD, 16);
        Color buttonColor = new Color(0, 102, 204);

        btnDepositar = createStyledButton("DEPOSITAR", buttonFont, buttonColor);
        btnRetirar = createStyledButton("RETIRAR", buttonFont, buttonColor);
        btnTransferir = createStyledButton("TRANSFERIR", buttonFont, buttonColor);
        btnSaldo = createStyledButton("VER SALDO", buttonFont, buttonColor);

        // Posicionamiento
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 0, 15, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        gbc.gridy = 0;
        mainPanel.add(btnDepositar, gbc);
        gbc.gridy = 1;
        mainPanel.add(btnRetirar, gbc);
        gbc.gridy = 2;
        mainPanel.add(btnTransferir, gbc);
        gbc.gridy = 3;
        mainPanel.add(btnSaldo, gbc);

        // Listeners
        btnDepositar.addActionListener(e -> new DepositoView());
        btnRetirar.addActionListener(e -> new RetiroView());
        btnTransferir.addActionListener(e -> new TransferenciaView());
        btnSaldo.addActionListener(e -> new SaldoView());

        add(mainPanel);
    }

    private JButton createStyledButton(String text, Font font, Color bgColor) {
        JButton button = new JButton(text);
        button.setFont(font);
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(200, 50));
        return button;
    }
}
