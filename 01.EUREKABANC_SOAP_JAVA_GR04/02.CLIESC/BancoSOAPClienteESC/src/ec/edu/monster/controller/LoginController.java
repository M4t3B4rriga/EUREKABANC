package ec.edu.monster.controller;
import ec.edu.monster.view.*;
import javax.swing.*;
public class LoginController {
    private final LoginView view;
    
    public LoginController(LoginView view) {
        this.view = view;
        initController();
    }
    
    private void initController() {
        view.btnIngresar.addActionListener(e -> {
            String usuario = view.txtUsuario.getText();
            String clave = new String(view.txtClave.getPassword());
            
            if ("MONSTER".equals(usuario) && "MONSTER9".equals(clave)) {
                BancoView bancoView = new BancoView();
                new BancoController(bancoView);
                bancoView.setVisible(true);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Credenciales incorrectas");
            }
        });
    }
}
