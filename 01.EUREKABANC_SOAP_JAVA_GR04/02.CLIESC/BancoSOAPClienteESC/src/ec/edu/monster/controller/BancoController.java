package ec.edu.monster.controller;
import ec.edu.monster.view.*;
import ec.edu.monster.service.BancoSOAPService;
import javax.swing.*;
import java.awt.event.ActionEvent;

public class BancoController {
    private final BancoView menuPrincipal; // Vista menú
    private DepositoView vistaDeposito;
    private RetiroView vistaRetiro;
    private SaldoView vistaSaldo;
    private TransferenciaView vistaTransferencia;
    private BancoSOAPService bancoService;
    
    public BancoController(BancoView menu) {
        this.menuPrincipal = menu;
        configurarMenu();
    }

    private void configurarMenu() {
        // 1. Botones principales (solo navegación)
        menuPrincipal.btnDepositar.addActionListener(e -> mostrarVistaDeposito());
        menuPrincipal.btnRetirar.addActionListener(e -> mostrarVistaRetiro());
        menuPrincipal.btnSaldo.addActionListener(e -> mostrarVistaSaldo());
        menuPrincipal.btnTransferir.addActionListener(e -> mostrarVistaTransferencia());
        
        
    }

    // 3. Métodos para mostrar vistas (sin lógica de negocio)
    private void mostrarVistaDeposito() {
        if (vistaDeposito == null) {
            vistaDeposito = new DepositoView();
            vistaDeposito.setLocationRelativeTo(menuPrincipal);
        }
        vistaDeposito.setVisible(true);
    }

    private void mostrarVistaRetiro() {
        if (vistaRetiro == null) {
            vistaRetiro = new RetiroView();
            vistaRetiro.setLocationRelativeTo(menuPrincipal);
        }
        vistaRetiro.setVisible(true);
    }

    private void mostrarVistaSaldo() {
        if (vistaSaldo == null) {
            vistaSaldo = new SaldoView();
            vistaSaldo.setLocationRelativeTo(menuPrincipal);
        }
        vistaSaldo.setVisible(true);
    }

    private void mostrarVistaTransferencia() {
        if (vistaTransferencia == null) {
            vistaTransferencia = new TransferenciaView();
            vistaTransferencia.setLocationRelativeTo(menuPrincipal);
        }
        vistaTransferencia.setVisible(true);
    }
}
