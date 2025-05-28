using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteEscritorioREST.ec.edu.monster.controlador
{
    public static class LoginController
    {
        public static bool ValidarCredenciales(string usuario, string contrasena)
        {
            return usuario.ToUpper() == "MONSTER" && contrasena == "MONSTER9";
        }
    }
}