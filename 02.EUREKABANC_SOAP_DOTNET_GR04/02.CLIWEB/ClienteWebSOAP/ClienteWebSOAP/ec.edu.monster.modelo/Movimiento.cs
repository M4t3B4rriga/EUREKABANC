using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ClienteWebSOAP.ec.edu.monster.modelo
{
    public class Movimiento
    {
        public string CodigoCuenta { get; set; }
        public int NumeroMovimiento { get; set; }
        public DateTime Fecha { get; set; }
        public string Tipo { get; set; }
        public decimal Importe { get; set; }
    }
}