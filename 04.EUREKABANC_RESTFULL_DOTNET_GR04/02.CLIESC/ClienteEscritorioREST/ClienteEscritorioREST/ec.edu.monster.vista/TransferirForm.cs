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
    public partial class TransferirForm : Form
    {
        private MenuForm menuForm;
        private BancoController controlador = new BancoController();

        public TransferirForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }

        private async void btnTransferir_Click(object sender, EventArgs e)
        {
            string origen = txtOrigen.Text.Trim();
            string destino = txtDestino.Text.Trim();
            string montoStr = txtMonto.Text.Trim();

            if (string.IsNullOrEmpty(origen) || string.IsNullOrEmpty(destino) || string.IsNullOrEmpty(montoStr))
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
                string resultado = await controlador.Transferir(origen, destino, monto);
                MessageBox.Show(resultado, "Resultado", MessageBoxButtons.OK, MessageBoxIcon.Information);
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al realizar la transferencia: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
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

        private void txtOrigen_TextChanged(object sender, EventArgs e)
        {

        }

        private void label3_Click(object sender, EventArgs e)
        {

        }

        private void txtDestino_TextChanged(object sender, EventArgs e)
        {

        }

        private void label4_Click(object sender, EventArgs e)
        {

        }

        private void txtMonto_TextChanged(object sender, EventArgs e)
        {

        }
    }
}
