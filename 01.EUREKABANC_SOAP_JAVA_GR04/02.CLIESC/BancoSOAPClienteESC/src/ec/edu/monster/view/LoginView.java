package ec.edu.monster.view;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class LoginView extends JFrame{
    public JTextField txtUsuario;
    public JPasswordField txtClave;
    public JButton btnIngresar;

    public LoginView() {
        setTitle("Login MonsterBank");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centra la ventana
        
        // Panel principal con imagen de fondo
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon background = new ImageIcon(getClass().getResource("/ec/edu/monster/img/Login.png"));
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(50, 50, 50, 50));

        // Panel de formulario (transparente)
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setOpaque(false);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel title = new JLabel("MONSTER BANK", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(title, gbc);

        // Campos de texto
        txtUsuario = new JTextField(20);
        txtClave = new JPasswordField(20);
        btnIngresar = new JButton("INGRESAR");
        btnIngresar.setBackground(new Color(0, 102, 204));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFont(new Font("Arial", Font.BOLD, 14));

        // Estilo de etiquetas
        JLabel lblUser = new JLabel("Usuario:");
        JLabel lblPass = new JLabel("Contraseña:");
        lblUser.setForeground(Color.WHITE);
        lblPass.setForeground(Color.WHITE);
        lblUser.setFont(new Font("Arial", Font.BOLD, 14));
        lblPass.setFont(new Font("Arial", Font.BOLD, 14));

        // Posicionamiento
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        formPanel.add(lblUser, gbc);
        
        gbc.gridy = 2;
        formPanel.add(lblPass, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(txtUsuario, gbc);
        
        gbc.gridy = 2;
        formPanel.add(txtClave, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.CENTER;
        formPanel.add(btnIngresar, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
}
