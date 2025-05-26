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
public class LoginVista extends JFrame {
    public LoginVista() {
        setTitle("Login");
        setSize(400, 300);
        setContentPane(new JLabel(new ImageIcon("recursos/Login.png")));
        setLayout(null);

        JLabel userLbl = new JLabel("Usuario:");
        JLabel passLbl = new JLabel("Clave:");
        JTextField userField = new JTextField();
        JPasswordField passField = new JPasswordField();
        JButton loginBtn = new JButton("Ingresar");

        userLbl.setBounds(100, 100, 80, 25);
        passLbl.setBounds(100, 130, 80, 25);
        userField.setBounds(180, 100, 120, 25);
        passField.setBounds(180, 130, 120, 25);
        loginBtn.setBounds(150, 180, 100, 30);

        add(userLbl); add(userField);
        add(passLbl); add(passField);
        add(loginBtn);

        loginBtn.addActionListener(e -> {
            String u = userField.getText();
            String p = new String(passField.getPassword());
            if ("MONSTER".equalsIgnoreCase(u) && "MONSTER9".equals(p)) {
                dispose();
                new MenuVista().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Credenciales incorrectas");
            }
        });

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
