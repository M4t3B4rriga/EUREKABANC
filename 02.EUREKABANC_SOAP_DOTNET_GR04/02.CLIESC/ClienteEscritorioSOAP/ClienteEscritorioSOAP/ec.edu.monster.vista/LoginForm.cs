using ClienteEscritorioSOAP.ec.edu.monster.modelo;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteEscritorioSOAP.ec.edu.monster.vista
{
    public partial class LoginForm : Form
    {
        public LoginForm()
        {
            InitializeComponent();
        }

        private void LoginForm_Load(object sender, EventArgs e)
        {

        }

        private void txtUsuario_TextChanged(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void txtContrasena_TextChanged(object sender, EventArgs e)
        {

        }

        private void btnLogin_Click(object sender, EventArgs e)
        {
            Usuario usuario = new Usuario(txtUsuario.Text, txtContrasena.Text);
            if (usuario.ValidarLogin())
            {
                this.Hide();
                MenuForm menu = new MenuForm();
                menu.ShowDialog();
                this.Close();
            }
            else
            {
                MessageBox.Show("Credenciales inválidas");
            }
        }
    }
}
