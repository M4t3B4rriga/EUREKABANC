using System;
using System.Windows.Forms;
using ClienteEscritorioREST.ec.edu.monster.vista;

namespace ClienteEscritorioREST
{
    static class Program
    {
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            // Iniciar la app con LoginForm
            LoginForm login = new LoginForm();
            Application.Run(login);
        }
    }
}
