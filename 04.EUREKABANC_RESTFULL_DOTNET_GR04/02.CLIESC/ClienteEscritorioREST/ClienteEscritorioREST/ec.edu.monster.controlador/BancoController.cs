using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Net.Http;
using Newtonsoft.Json;
using ClienteEscritorioREST.ec.edu.monster.modelo;

namespace ClienteEscritorioREST.ec.edu.monster.controlador
{
    public class BancoController
    {
        private readonly string baseUrl = "https://localhost:7079/api/Banco"; // Cambia al puerto/IP de tu API REST

        public async Task<string> DepositarAsync(string cuenta, decimal monto)
        {
            using (HttpClient client = new HttpClient())
            {
                var response = await client.PostAsync($"{baseUrl}/Depositar?cuenta={cuenta}&monto={monto}", null);
                return await response.Content.ReadAsStringAsync();
            }
        }

        public async Task<string> Retirar(string cuenta, decimal monto)
        {
            using (HttpClient client = new HttpClient())
            {
                var response = await client.PostAsync($"{baseUrl}/Retirar?cuenta={cuenta}&monto={monto}", null);
                return await response.Content.ReadAsStringAsync();
            }
        }

        public async Task<string> Transferir(string cuentaOrigen, string cuentaDestino, decimal monto)
        {
            using (HttpClient client = new HttpClient())
            {
                var response = await client.PostAsync($"{baseUrl}/Transferir?cuentaOrigen={cuentaOrigen}&cuentaDestino={cuentaDestino}&monto={monto}", null);
                return await response.Content.ReadAsStringAsync();
            }
        }

        public async Task<string> ConsultarSaldo(string cuenta)
        {
            using (HttpClient client = new HttpClient())
            {
                return await client.GetStringAsync($"{baseUrl}/Saldo?cuenta={cuenta}");
            }
        }

        public async Task<List<Movimiento>> ConsultarMovimientos(string cuenta)
        {
            using (HttpClient client = new HttpClient())
            {
                var json = await client.GetStringAsync($"{baseUrl}/Movimientos?cuenta={cuenta}");
                return JsonConvert.DeserializeObject<List<Movimiento>>(json);
            }
        }
    }
}