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
using ClienteEscritorioREST.ec.edu.monster.modelo;

namespace ClienteEscritorioREST.ec.edu.monster.vista
{
    public partial class MovimientosForm : Form
    {
        private MenuForm menuForm;
        private BancoController controlador = new BancoController();

        public MovimientosForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }

        private async void btnConsultarMovimientos_Click(object sender, EventArgs e)
        {
            string cuenta = txtCuenta.Text.Trim();
            if (string.IsNullOrEmpty(cuenta))
            {
                MessageBox.Show("Ingrese un número de cuenta.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                var lista = await controlador.ConsultarMovimientos(cuenta);
                dgvMovimientos.DataSource = lista;
            }
            catch (Exception ex)
            {
                MessageBox.Show("Error al consultar movimientos: " + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }


        private void btnRegresar_Click(object sender, EventArgs e)
        {
            this.Close();
            menuForm.Show();
        }

        private void dgvMovimientos_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            // Opcional: implementar si quieres manejar clics en la grilla
        }

        private void txtCuenta_TextChanged(object sender, EventArgs e)
        {
        }

        private void label1_Click(object sender, EventArgs e)
        {
        }

        private void label2_Click(object sender, EventArgs e)
        {
        }

        private void MovimientosForm_Load(object sender, EventArgs e)
        {
        }
    }
}
