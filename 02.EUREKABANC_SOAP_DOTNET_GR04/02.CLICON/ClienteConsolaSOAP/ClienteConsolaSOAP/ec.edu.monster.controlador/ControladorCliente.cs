using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClienteConsolaSOAP.ec.edu.monster.modelo;
using ClienteConsolaSOAP.ec_edu.monster.vista;

namespace ClienteConsolaSOAP.ec.edu.monster.controlador
{
    public class ControladorCliente
    {
        public static void Iniciar()
        {
            VistaLogin login = new VistaLogin();
            Usuario usuario = login.MostrarLogin();

            if (usuario != null && usuario.ValidarLogin())
            {
                VistaMenu.MostrarMenu();
            }
            else
            {
                Console.WriteLine("Credenciales inválidas. Finalizando...");
            }
        }
    }
}