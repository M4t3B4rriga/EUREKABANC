using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClienteConsolaSOAP.ec.edu.monster.modelo;

namespace ClienteConsolaSOAP.ec_edu.monster.vista
{
    public class VistaLogin
    {
        public Usuario MostrarLogin()
        {
            Console.Write("Ingrese usuario: ");
            string user = Console.ReadLine();
            Console.Write("Ingrese contraseña: ");
            string pass = Console.ReadLine();
            return new Usuario(user, pass);
        }
    }
}