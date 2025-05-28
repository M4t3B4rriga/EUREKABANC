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
    public partial class RetirarForm : Form
    {
        private MenuForm menuForm;
        private BancoController controlador = new BancoController();

        public RetirarForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }

        private async void btnRetirar_Click(object sender, EventArgs e)
        {
            string cuenta = txtCuenta.Text.Trim();
            string montoStr = txtMonto.Text.Trim();

            if (string.IsNullOrEmpty(cuenta) || string.IsNullOrEmpty(montoStr))
            {
                MessageBox.Show("Por favor, complete todos los campos.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            if (!decimal.TryParse(montoStr, out decimal monto) || monto <= 0)
            {
                MessageBox.Show("Ingrese un monto válido.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                string resultado = await controlador.Retirar(cuenta, monto);
                MessageBox.Show(resultado, "Resultado", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al realizar el retiro: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
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
    }
}
