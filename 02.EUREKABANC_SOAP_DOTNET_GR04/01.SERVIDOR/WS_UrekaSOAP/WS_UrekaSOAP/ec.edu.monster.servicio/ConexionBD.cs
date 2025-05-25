using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;


namespace WS_UrekaSOAP.ec.edu.monster.servicio
{
    public class ConexionBD
    {
        private readonly string connectionString = "Server=DESKTOP-GM48FCK;Database=eureka;User Id=sa;Password=Adali020;TrustServerCertificate=True;";

        public SqlConnection GetConnection()
        {
            return new SqlConnection(connectionString);
        }
    }
}