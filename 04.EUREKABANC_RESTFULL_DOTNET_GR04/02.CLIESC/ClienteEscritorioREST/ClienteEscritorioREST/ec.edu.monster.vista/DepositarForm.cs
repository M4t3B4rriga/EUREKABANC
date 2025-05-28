using ClienteEscritorioREST.ec.edu.monster.controlador;
using System;
using System.Windows.Forms;

namespace ClienteEscritorioREST.ec.edu.monster.vista
{
    public partial class DepositarForm : Form
    {
        private MenuForm menuForm;
        private BancoController controlador = new BancoController();

        public DepositarForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }

        private async void btnDepositar_Click(object sender, EventArgs e)
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
                string resultado = await controlador.DepositarAsync(cuenta, monto);
                MessageBox.Show(resultado, "Resultado", MessageBoxButtons.OK, MessageBoxIcon.Information);
            } 
            catch (Exception ex)
            {
                MessageBox.Show("Error al realizar el depósito: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }
        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
        }

        private void DepositarForm_Load(object sender, EventArgs e)
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
