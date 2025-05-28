using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using ClienteEscritorioREST.ec.edu.monster.controlador;

namespace ClienteEscritorioREST.ec.edu.monster.vista
{
    public partial class SaldoForm : Form
    {
        private MenuForm menuForm;
        private BancoController controlador = new BancoController();

        public SaldoForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }

        private async void btnConsultar_Click(object sender, EventArgs e)
        {
            string cuenta = txtCuenta.Text.Trim();
            if (string.IsNullOrEmpty(cuenta))
            {
                MessageBox.Show("Ingrese un número de cuenta.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                string resultado = await controlador.ConsultarSaldo(cuenta);
                MessageBox.Show(resultado, "Saldo Actual", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al consultar el saldo: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
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
    }
}
