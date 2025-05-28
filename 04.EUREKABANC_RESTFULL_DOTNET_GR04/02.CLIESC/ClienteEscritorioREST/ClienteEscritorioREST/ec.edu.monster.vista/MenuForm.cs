using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ClienteEscritorioREST.ec.edu.monster.vista
{
    public partial class MenuForm : Form
    {
        public MenuForm()
        {
            InitializeComponent();
        }

        private void MenuForm_Load(object sender, EventArgs e)
        {

        }

        private void lblTitulo_Click(object sender, EventArgs e)
        {

        }

        private void btnDepositar_Click(object sender, EventArgs e)
        {
            using (DepositarForm depositarForm = new DepositarForm(this))
            {
                depositarForm.ShowDialog();
            }


        }

        private void btnRetirar_Click(object sender, EventArgs e)
        {
            using (RetirarForm retirarForm = new RetirarForm(this))
            {
                retirarForm.ShowDialog();
            }

        }

        private void btnTransferir_Click(object sender, EventArgs e)
        {
            using (TransferirForm transferirForm = new TransferirForm(this))
            {
                transferirForm.ShowDialog();
            }

        }

        private void btnSaldo_Click(object sender, EventArgs e)
        {
            using (SaldoForm saldoForm = new SaldoForm(this))
            {
                saldoForm.ShowDialog();
            }

        }

        private void btnMovimientos_Click(object sender, EventArgs e)
        {
            using (MovimientosForm movimientosForm = new MovimientosForm(this))
            {
                movimientosForm.ShowDialog();
            }

        }

        private void btnCerrarSesion_Click(object sender, EventArgs e)
        {
            this.Close();
            Application.OpenForms["LoginForm"].Show();
        }
    }
}
