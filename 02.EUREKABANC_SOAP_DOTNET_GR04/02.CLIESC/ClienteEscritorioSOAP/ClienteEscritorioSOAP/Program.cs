using ClienteEscritorioSOAP.ec.edu.monster.controlador;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteEscritorioSOAP
{
    static class Program
    {
        [STAThread]
        static void Main()
        {
            ControladorCliente.Iniciar();
        }
    }
}
