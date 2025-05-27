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




namespace ClienteEscritorioSOAP.ec.edu.monster.vista
{
    public partial class MovimientosForm : Form
    {
        private MenuForm menuForm;
        ServicioBancarioSoapClient cliente = new ServicioBancarioSoapClient();

        public MovimientosForm(MenuForm menu)
        {
            InitializeComponent();
            this.menuForm = menu;
        }

        private void MovimientosForm_Load(object sender, EventArgs e)
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

        private void btnConsultarMovimientos_Click(object sender, EventArgs e)
        {
            string cuenta = txtCuenta.Text.Trim();

            if (string.IsNullOrEmpty(cuenta))
            {
                MessageBox.Show("Por favor, ingrese un número de cuenta.", "Advertencia", MessageBoxButtons.OK, MessageBoxIcon.Warning);
                return;
            }

            try
            {
                var movimientos = cliente.ConsultarMovimientos(cuenta);

                if (movimientos != null && movimientos.Tables.Count > 0)
                {
                    DataTable dt = movimientos.Tables[0];
                    var filas = dt.Select("", "dtt_movifecha DESC");
                    dgvMovimientos.DataSource = filas.Length > 0 ? filas.CopyToDataTable() : null;
                }
                else
                {
                    MessageBox.Show("No se encontraron movimientos.");
                    dgvMovimientos.DataSource = null;
                }
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

        }
    }
}
