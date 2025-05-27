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
    public partial class SaldoForm : Form
    {
        private MenuForm menuForm;
        ServicioBancarioSoapClient cliente = new ServicioBancarioSoapClient();

        public SaldoForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }

        private void SaldoForm_Load(object sender, EventArgs e)
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

        private void btnConsultarSaldo_Click(object sender, EventArgs e)
        {
            string cuenta = txtCuenta.Text;
            decimal saldo = cliente.ConsultarSaldo(cuenta);
            MessageBox.Show($"El saldo actual de la cuenta {cuenta} es: {saldo}");
        }
        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
        }
    }
}
