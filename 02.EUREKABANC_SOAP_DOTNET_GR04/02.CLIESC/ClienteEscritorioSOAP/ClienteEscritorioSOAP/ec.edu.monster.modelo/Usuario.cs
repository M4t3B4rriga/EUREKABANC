using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteEscritorioSOAP.ec.edu.monster.modelo
{
    public class Usuario
    {
        public string Username { get; set; }
        public string Password { get; set; }

        public Usuario(string username, string password)
        {
            Username = username;
            Password = password;
        }

        public bool ValidarLogin()
        {
            return Username.ToUpper() == "MONSTER" && Password == "MONSTER9";
        }
    }
}