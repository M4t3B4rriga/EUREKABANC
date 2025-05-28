using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteEscritorioREST.ec.edu.monster.modelo
{
    public class Movimiento   // <-- Asegúrate de que sea public
    {
        public string chr_cuencodigo { get; set; }
        public int int_movinumero { get; set; }
        public DateTime dtt_movifecha { get; set; }
        public string chr_emplcodigo { get; set; }
        public string chr_tipocodigo { get; set; }
        public decimal dec_moviimporte { get; set; }
        public string chr_cuenreferencia { get; set; }
    }
}
