using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Services;
using WS_UrekaSOAP.ec.edu.monster.servicio;

namespace WS_UrekaSOAP
{
    [WebService(Namespace = "https://localhost:44300/ServicioUreka.asmx")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    public class ServicioUreka : WebService
    {
        ConexionBD conexion = new ConexionBD();

        [WebMethod]
        public string PruebaConexion()
        {
            try
            {
                using (var conn = conexion.GetConnection())
                {
                    conn.Open();
                    return "Conexión exitosa a la base de datos Ureka.";
                }
            }
            catch (Exception ex)
            {
                return $"Error de conexión: {ex.Message}";
            }
        }

        // ---------- CLIENTE ----------

        [WebMethod]
        public string AgregarCliente(string codigo, string paterno, string materno, string nombre, string dni, string ciudad, string direccion, string telefono, string email)
        {
            try
            {
                using (var conn = conexion.GetConnection())
                {
                    conn.Open();
                    string query = @"INSERT INTO Cliente (chr_cliecodigo, vch_cliepaterno, vch_cliematerno, vch_clienombre, chr_cliedni, vch_clieciudad, vch_cliedireccion, vch_clietelefono, vch_clieemail)
                                     VALUES (@codigo, @paterno, @materno, @nombre, @dni, @ciudad, @direccion, @telefono, @email)";

                    using (var cmd = new SqlCommand(query, conn))
                    {
                        cmd.Parameters.AddWithValue("@codigo", codigo);
                        cmd.Parameters.AddWithValue("@paterno", paterno);
                        cmd.Parameters.AddWithValue("@materno", materno);
                        cmd.Parameters.AddWithValue("@nombre", nombre);
                        cmd.Parameters.AddWithValue("@dni", dni);
                        cmd.Parameters.AddWithValue("@ciudad", ciudad);
                        cmd.Parameters.AddWithValue("@direccion", direccion);
                        cmd.Parameters.AddWithValue("@telefono", telefono);
                        cmd.Parameters.AddWithValue("@email", email);

                        int rows = cmd.ExecuteNonQuery();
                        return rows > 0 ? "Cliente agregado correctamente." : "Error al agregar cliente.";
                    }
                }
            }
            catch (Exception ex)
            {
                return "Error: " + ex.Message;
            }
        }

        [WebMethod]
        public List<string> ListarClientes()
        {
            var clientes = new List<string>();

            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                string query = "SELECT vch_clienombre FROM Cliente";

                using (var cmd = new SqlCommand(query, conn))
                using (var reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        clientes.Add(reader["vch_clienombre"].ToString());
                    }
                }
            }

            return clientes;
        }

        // ---------- CUENTA ----------

        [WebMethod]
        public string AgregarCuenta(string cuencodigo, string monecodigo, string sucucodigo, string emplcodigo, string cliecodigo, decimal saldo, string clave)
        {
            try
            {
                using (var conn = conexion.GetConnection())
                {
                    conn.Open();
                    string query = @"INSERT INTO Cuenta (chr_cuencodigo, chr_monecodigo, chr_sucucodigo, chr_emplcreacuenta, chr_cliecodigo, dec_cuensaldo, dtt_cuenfechacreacion, vch_cuenestado, int_cuencontmov, chr_cuenclave)
                                     VALUES (@cuencodigo, @monecodigo, @sucucodigo, @emplcodigo, @cliecodigo, @saldo, GETDATE(), 'ACTIVO', 0, @clave)";

                    using (var cmd = new SqlCommand(query, conn))
                    {
                        cmd.Parameters.AddWithValue("@cuencodigo", cuencodigo);
                        cmd.Parameters.AddWithValue("@monecodigo", monecodigo);
                        cmd.Parameters.AddWithValue("@sucucodigo", sucucodigo);
                        cmd.Parameters.AddWithValue("@emplcodigo", emplcodigo);
                        cmd.Parameters.AddWithValue("@cliecodigo", cliecodigo);
                        cmd.Parameters.AddWithValue("@saldo", saldo);
                        cmd.Parameters.AddWithValue("@clave", clave);

                        int rows = cmd.ExecuteNonQuery();
                        return rows > 0 ? "Cuenta agregada correctamente." : "Error al agregar cuenta.";
                    }
                }
            }
            catch (Exception ex)
            {
                return "Error: " + ex.Message;
            }
        }

        [WebMethod]
        public List<string> ListarCuentas()
        {
            var cuentas = new List<string>();

            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                string query = "SELECT chr_cuencodigo FROM Cuenta";

                using (var cmd = new SqlCommand(query, conn))
                using (var reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        cuentas.Add(reader["chr_cuencodigo"].ToString());
                    }
                }
            }

            return cuentas;
        }

        // ---------- MOVIMIENTO ----------

        [WebMethod]
        public string AgregarMovimiento(string cuencodigo, string emplcodigo, string tipocodigo, decimal importe)
        {
            try
            {
                using (var conn = conexion.GetConnection())
                {
                    conn.Open();
                    // Obtener el número de movimiento actual
                    string selectQuery = "SELECT ISNULL(MAX(int_movinumero),0) + 1 FROM Movimiento WHERE chr_cuencodigo = @cuencodigo";
                    int nuevoNumero = 1;

                    using (var cmdSelect = new SqlCommand(selectQuery, conn))
                    {
                        cmdSelect.Parameters.AddWithValue("@cuencodigo", cuencodigo);
                        nuevoNumero = (int)cmdSelect.ExecuteScalar();
                    }

                    string insertQuery = @"INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte)
                                           VALUES (@cuencodigo, @numero, GETDATE(), @emplcodigo, @tipocodigo, @importe)";

                    using (var cmdInsert = new SqlCommand(insertQuery, conn))
                    {
                        cmdInsert.Parameters.AddWithValue("@cuencodigo", cuencodigo);
                        cmdInsert.Parameters.AddWithValue("@numero", nuevoNumero);
                        cmdInsert.Parameters.AddWithValue("@emplcodigo", emplcodigo);
                        cmdInsert.Parameters.AddWithValue("@tipocodigo", tipocodigo);
                        cmdInsert.Parameters.AddWithValue("@importe", importe);

                        int rows = cmdInsert.ExecuteNonQuery();
                        return rows > 0 ? "Movimiento registrado correctamente." : "Error al registrar movimiento.";
                    }
                }
            }
            catch (Exception ex)
            {
                return "Error: " + ex.Message;
            }
        }

        [WebMethod]
        public List<string> ListarMovimientos(string cuencodigo)
        {
            var movimientos = new List<string>();

            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                string query = @"SELECT int_movinumero, dec_moviimporte FROM Movimiento WHERE chr_cuencodigo = @cuencodigo";

                using (var cmd = new SqlCommand(query, conn))
                {
                    cmd.Parameters.AddWithValue("@cuencodigo", cuencodigo);

                    using (var reader = cmd.ExecuteReader())
                    {
                        while (reader.Read())
                        {
                            movimientos.Add($"N°: {reader["int_movinumero"]} - Importe: {reader["dec_moviimporte"]}");
                        }
                    }
                }
            }

            return movimientos;
        }

        // ---------- CONSULTAS AUXILIARES ----------

        [WebMethod]
        public List<string> ListarMonedas()
        {
            var monedas = new List<string>();

            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                string query = "SELECT vch_monedescripcion FROM Moneda";

                using (var cmd = new SqlCommand(query, conn))
                using (var reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        monedas.Add(reader["vch_monedescripcion"].ToString());
                    }
                }
            }

            return monedas;
        }

        [WebMethod]
        public List<string> ListarSucursales()
        {
            var sucursales = new List<string>();

            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                string query = "SELECT vch_sucunombre FROM Sucursal";

                using (var cmd = new SqlCommand(query, conn))
                using (var reader = cmd.ExecuteReader())
                {
                    while (reader.Read())
                    {
                        sucursales.Add(reader["vch_sucunombre"].ToString());
                    }
                }
            }

            return sucursales;
        }
    }
}