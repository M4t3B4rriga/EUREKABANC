package ec.edu.monster;
import ec.edu.monster.view.LoginView;
import ec.edu.monster.controller.LoginController;
public class Main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            LoginView loginView = new LoginView();
            new LoginController(loginView);
            loginView.setVisible(true);
        });
    }
}
