using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClienteEscritorioSOAP.ec.edu.monster.vista;
using System.Windows.Forms;

namespace ClienteEscritorioSOAP.ec.edu.monster.controlador
{
    public static class ControladorCliente
    {
        [STAThread]
        public static void Iniciar()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new LoginForm());
        }
    }
}