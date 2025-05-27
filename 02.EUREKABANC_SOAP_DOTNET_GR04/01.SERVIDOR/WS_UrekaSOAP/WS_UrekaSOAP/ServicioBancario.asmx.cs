using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;
using System.Web.Services;
using WS_UrekaSOAP.ec.edu.monster.servicio;

namespace WS_UrekaSOAP
{
    [WebService(Namespace = "http://tempuri.org/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    public class ServicioBancario : System.Web.Services.WebService
    {
        ConexionBD conexion = new ConexionBD();



        [WebMethod]
        public string Depositar(string cuenta, decimal monto)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand("UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + @monto, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = @cuenta", conn);
                cmd.Parameters.AddWithValue("@monto", monto);
                cmd.Parameters.AddWithValue("@cuenta", cuenta);
                int filas = cmd.ExecuteNonQuery();

                if (filas > 0)
                {
                    RegistrarMovimiento(cuenta, monto, "003"); // 003 = Deposito
                    return "Depósito realizado correctamente.";
                }
                else return "Cuenta no encontrada.";
            }
        }

        [WebMethod]
        public string Retirar(string cuenta, decimal monto)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand("UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - @monto, int_cuencontmov = int_cuencontmov + 1 WHERE chr_cuencodigo = @cuenta AND dec_cuensaldo >= @monto", conn);
                cmd.Parameters.AddWithValue("@monto", monto);
                cmd.Parameters.AddWithValue("@cuenta", cuenta);
                int filas = cmd.ExecuteNonQuery();

                if (filas > 0)
                {
                    RegistrarMovimiento(cuenta, monto, "004"); // 004 = Retiro
                    return "Retiro realizado correctamente.";
                }
                else return "Fondos insuficientes o cuenta no encontrada.";
            }
        }

        [WebMethod]
        public string Transferir(string cuentaOrigen, string cuentaDestino, decimal monto)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlTransaction trans = conn.BeginTransaction();

                try
                {
                    SqlCommand retiro = new SqlCommand("UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - @monto WHERE chr_cuencodigo = @cuenta AND dec_cuensaldo >= @monto", conn, trans);
                    retiro.Parameters.AddWithValue("@monto", monto);
                    retiro.Parameters.AddWithValue("@cuenta", cuentaOrigen);

                    SqlCommand deposito = new SqlCommand("UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + @monto WHERE chr_cuencodigo = @cuenta", conn, trans);
                    deposito.Parameters.AddWithValue("@monto", monto);
                    deposito.Parameters.AddWithValue("@cuenta", cuentaDestino);

                    int filasRetiro = retiro.ExecuteNonQuery();
                    int filasDeposito = deposito.ExecuteNonQuery();

                    if (filasRetiro > 0 && filasDeposito > 0)
                    {
                        RegistrarMovimiento(cuentaOrigen, monto, "009", trans); // 009 = Transferencia Salida
                        RegistrarMovimiento(cuentaDestino, monto, "008", trans); // 008 = Transferencia Entrada
                        trans.Commit();
                        return "Transferencia realizada correctamente.";
                    }
                    else
                    {
                        trans.Rollback();
                        return "Error en la transferencia (fondos o cuentas).";
                    }
                }
                catch
                {
                    trans.Rollback();
                    return "Error en la transferencia.";
                }
            }
        }

        [WebMethod]
        public decimal ConsultarSaldo(string cuenta)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand("SELECT dec_cuensaldo FROM Cuenta WHERE chr_cuencodigo = @cuenta", conn);
                cmd.Parameters.AddWithValue("@cuenta", cuenta);
                object saldo = cmd.ExecuteScalar();
                return saldo != null ? Convert.ToDecimal(saldo) : 0;
            }
        }

        [WebMethod]
        public DataSet ConsultarMovimientos(string cuenta)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlDataAdapter da = new SqlDataAdapter("SELECT * FROM Movimiento WHERE chr_cuencodigo = @cuenta", conn);
                da.SelectCommand.Parameters.AddWithValue("@cuenta", cuenta);
                DataSet ds = new DataSet();
                da.Fill(ds);
                return ds;
            }
        }

        // Método auxiliar para registrar movimientos (empleado se marca como 'SYSTEM' por defecto)
        private void RegistrarMovimiento(string cuenta, decimal monto, string tipo, SqlTransaction trans = null)
        {
            using (var conn = trans != null ? null : conexion.GetConnection())
            {
                if (conn != null) conn.Open();

                SqlCommand cmd = new SqlCommand(@"
                    INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte)
                    VALUES (@cuenta, (SELECT ISNULL(MAX(int_movinumero), 0) + 1 FROM Movimiento WHERE chr_cuencodigo = @cuenta), GETDATE(), @empleado, @tipo, @monto)",
                    conn ?? trans.Connection, trans);

                cmd.Parameters.AddWithValue("@cuenta", cuenta);
                cmd.Parameters.AddWithValue("@empleado", "9999"); // Código genérico para SYSTEM
                cmd.Parameters.AddWithValue("@tipo", tipo);
                cmd.Parameters.AddWithValue("@monto", monto);
                cmd.ExecuteNonQuery();
            }
        }
    }
}
