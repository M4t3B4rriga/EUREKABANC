using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClienteEscritorioSOAP.ServicioBancario;

namespace ClienteEscritorioSOAP.ec.edu.monster.vista
{
    public partial class TransferirForm : Form
    {
        private MenuForm menuForm;
        ServicioBancarioSoapClient cliente = new ServicioBancarioSoapClient();

        public TransferirForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }
        private void TransferirForm_Load(object sender, EventArgs e)
        {

        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void label2_Click(object sender, EventArgs e)
        {

        }

        private void txtCuentaOrigen_TextChanged(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void txtCuentaDestino_TextChanged(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void txtMonto_TextChanged(object sender, EventArgs e)
        {

        }

        private void btnTransferir_Click(object sender, EventArgs e)
        {
            string cuentaOrigen = txtCuentaOrigen.Text;
            string cuentaDestino = txtCuentaDestino.Text;
            decimal monto = decimal.Parse(txtMonto.Text);

            string resultado = cliente.Transferir(cuentaOrigen, cuentaDestino, monto);
            MessageBox.Show(resultado);
        }

        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
        }
    }
}
