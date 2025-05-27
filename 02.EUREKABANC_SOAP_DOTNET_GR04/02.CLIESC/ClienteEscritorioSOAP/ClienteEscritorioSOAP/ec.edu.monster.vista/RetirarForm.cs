using ClienteEscritorioSOAP.ServicioBancario;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using static System.Windows.Forms.VisualStyles.VisualStyleElement;

namespace ClienteEscritorioSOAP.ec.edu.monster.vista
{
    public partial class RetirarForm : Form
    {
        private MenuForm menuForm;
        ServicioBancarioSoapClient cliente = new ServicioBancarioSoapClient();

        public RetirarForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }


        private void RetirarForm_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void txtCuenta_TextChanged(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void txtMonto_TextChanged(object sender, EventArgs e)
        {

        }

        private void btnRetirar_Click(object sender, EventArgs e)
        {
            string cuenta = txtCuenta.Text;
            decimal monto = decimal.Parse(txtMonto.Text);
            MessageBox.Show(cliente.Retirar(cuenta, monto));
        }

        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
        }
    }
}
