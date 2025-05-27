namespace ApiBancariaREST.ec.edu.monster.modelo
{
    public class Movimiento
    {
        public string chr_cuencodigo { get; set; }
        public int int_movinumero { get; set; }
        public DateTime dtt_movifecha { get; set; }
        public string chr_tipocodigo { get; set; }
        public decimal dec_moviimporte { get; set; }
        public string chr_cuenreferencia { get; set; }
    }
}