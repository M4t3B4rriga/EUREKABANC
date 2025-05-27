using ApiBancariaREST.ec.edu.monster.modelo;
using ApiBancariaREST.ec.edu.monster.servicio;
using ApiBancariaREST.ec.edu.monster.modelo;
using ApiBancariaREST.ec.edu.monster.servicio;
using Microsoft.AspNetCore.Mvc;  // Si es ASP.NET Core
using System.Data;
using System.Data.SqlClient;

namespace ApiBancariaREST.ec.edu.monster.controlador
{
    [ApiController]
    [Route("api/[controller]")]
    public class BancoController : ControllerBase
    {
        private readonly ConexionBD conexion = new ConexionBD();

        [HttpPost("Depositar")]
        public IActionResult Depositar(string cuenta, decimal monto)
        {
            try
            {
                using (var conn = conexion.GetConnection())
                {
                    conn.Open();
                    string sql = @"
                        UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + @monto WHERE chr_cuencodigo = @cuenta;

                        DECLARE @num INT = ISNULL((SELECT MAX(int_movinumero) FROM Movimiento WHERE chr_cuencodigo = @cuenta), 0) + 1;
                        INSERT INTO Movimiento (chr_cuencodigo, int_movinumero, dtt_movifecha, chr_emplcodigo, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia)
                        VALUES (@cuenta, @num, GETDATE(), '0001', '003', @monto, NULL);";


                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.Parameters.AddWithValue("@cuenta", cuenta);
                    cmd.Parameters.AddWithValue("@monto", monto);

                    int filas = cmd.ExecuteNonQuery();
                    if (filas > 0)
                        return Ok($"Depósito de {monto} realizado en la cuenta {cuenta}.");
                    else
                        return NotFound("Cuenta no encontrada.");
                }
            }
            catch (Exception ex)
            {
                return BadRequest($"Error: {ex.Message}");
            }
        }

        [HttpPost("Retirar")]
        public IActionResult Retirar(string cuenta, decimal monto)
        {
            try
            {
                using (var conn = conexion.GetConnection())
                {
                    conn.Open();
                    string sql = "UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - @monto WHERE chr_cuencodigo = @cuenta AND dec_cuensaldo >= @monto";
                    SqlCommand cmd = new SqlCommand(sql, conn);
                    cmd.Parameters.AddWithValue("@cuenta", cuenta);
                    cmd.Parameters.AddWithValue("@monto", monto);

                    int filas = cmd.ExecuteNonQuery();
                    if (filas > 0)
                        return Ok($"Retiro de {monto} realizado en la cuenta {cuenta}.");
                    else
                        return BadRequest("Fondos insuficientes o cuenta no encontrada.");
                }
            }
            catch (Exception ex)
            {
                return BadRequest($"Error: {ex.Message}");
            }
        }

        [HttpPost("Transferir")]
        public IActionResult Transferir(string origen, string destino, decimal monto)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlTransaction tran = conn.BeginTransaction();

                try
                {
                    SqlCommand cmd1 = new SqlCommand("UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo - @monto WHERE chr_cuencodigo = @origen AND dec_cuensaldo >= @monto", conn, tran);
                    cmd1.Parameters.AddWithValue("@origen", origen);
                    cmd1.Parameters.AddWithValue("@monto", monto);
                    int filas1 = cmd1.ExecuteNonQuery();

                    SqlCommand cmd2 = new SqlCommand("UPDATE Cuenta SET dec_cuensaldo = dec_cuensaldo + @monto WHERE chr_cuencodigo = @destino", conn, tran);
                    cmd2.Parameters.AddWithValue("@destino", destino);
                    cmd2.Parameters.AddWithValue("@monto", monto);
                    int filas2 = cmd2.ExecuteNonQuery();

                    if (filas1 > 0 && filas2 > 0)
                    {
                        tran.Commit();
                        return Ok($"Transferencia de {monto} realizada de {origen} a {destino}.");
                    }
                    else
                    {
                        tran.Rollback();
                        return BadRequest("Error: Cuenta origen/destino no encontrada o saldo insuficiente.");
                    }
                }
                catch (Exception ex)
                {
                    tran.Rollback();
                    return BadRequest($"Error: {ex.Message}");
                }
            }
        }

        [HttpGet("Saldo")]
        public IActionResult Saldo(string cuenta)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand("SELECT dec_cuensaldo FROM Cuenta WHERE chr_cuencodigo = @cuenta", conn);
                cmd.Parameters.AddWithValue("@cuenta", cuenta);
                var saldo = cmd.ExecuteScalar();

                if (saldo != null)
                    return Ok(new { cuenta = cuenta, saldo = saldo });
                else
                    return NotFound("Cuenta no encontrada.");
            }
        }

        [HttpGet("Movimientos")]
        public IActionResult Movimientos(string cuenta)
        {
            using (var conn = conexion.GetConnection())
            {
                conn.Open();
                SqlCommand cmd = new SqlCommand("SELECT chr_cuencodigo, int_movinumero, dtt_movifecha, chr_tipocodigo, dec_moviimporte, chr_cuenreferencia FROM Movimiento WHERE chr_cuencodigo = @cuenta ORDER BY dtt_movifecha DESC", conn);
                cmd.Parameters.AddWithValue("@cuenta", cuenta);
                SqlDataReader reader = cmd.ExecuteReader();

                var lista = new List<Movimiento>();
                while (reader.Read())
                {
                    lista.Add(new Movimiento
                    {
                        chr_cuencodigo = reader["chr_cuencodigo"].ToString(),
                        int_movinumero = Convert.ToInt32(reader["int_movinumero"]),
                        dtt_movifecha = Convert.ToDateTime(reader["dtt_movifecha"]),
                        chr_tipocodigo = reader["chr_tipocodigo"].ToString(),
                        dec_moviimporte = Convert.ToDecimal(reader["dec_moviimporte"]),
                        chr_cuenreferencia = reader["chr_cuenreferencia"]?.ToString()
                    });
                }

                return Ok(lista);
            }
        }
    }
}