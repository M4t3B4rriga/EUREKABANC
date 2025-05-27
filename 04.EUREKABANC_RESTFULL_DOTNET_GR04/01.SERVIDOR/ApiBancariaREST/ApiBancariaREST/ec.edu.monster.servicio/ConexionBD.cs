using System.Data.SqlClient;

namespace ApiBancariaREST.ec.edu.monster.servicio
{
    public class ConexionBD
    {
        private readonly string cadena = @"Server=DESKTOP-GM48FCK;Database=Bank;User Id=sa;Password=Adali020;";

        public SqlConnection GetConnection()
        {
            return new SqlConnection(cadena);
        }
    }
}
