using ClienteWebREST.ec.edu.monster.modelo;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Mvc;

namespace ec.edu.monster.controlador
{
    public class BancoController : Controller
    {
        private readonly string baseUrl = "https://localhost:7079/api/Banco"; // Cambia tu URL si es necesario
        private readonly HttpClient client = new HttpClient();

        // Login GET
        public ActionResult Login()
        {
            return View("~/Views/Login/Index.cshtml");
        }

        // Login POST
        [HttpPost]
        public ActionResult Login(string usuario, string contrasena)
        {
            if (usuario.ToUpper() == "MONSTER" && contrasena == "MONSTER9")
                return RedirectToAction("Index");
            else
            {
                ViewBag.Error = "Credenciales inválidas";
                return View("~/Views/Login/Index.cshtml");
            }
        }

        // Menú principal
        public ActionResult Index()
        {
            return View("~/Views/Banco/Index.cshtml");
        }

        // === Vistas GET ===

        public ActionResult Depositar()
        {
            return View("~/Views/Banco/Depositar.cshtml");
        }

        public ActionResult Retirar()
        {
            return View("~/Views/Banco/Retirar.cshtml");
        }

        public ActionResult Transferir()
        {
            return View("~/Views/Banco/Transferir.cshtml");
        }

        public ActionResult Saldo()
        {
            return View("~/Views/Banco/Saldo.cshtml");
        }

        public ActionResult Movimientos()
        {
            return View("~/Views/Banco/Movimientos.cshtml");
        }

        // === Lógica POST ===

        [HttpPost]
        public async Task<ActionResult> Depositar(string cuenta, decimal monto)
        {
            var response = await client.PostAsync($"{baseUrl}/Depositar?cuenta={cuenta}&monto={monto}", null);
            ViewBag.Mensaje = await response.Content.ReadAsStringAsync();
            return View("~/Views/Banco/Depositar.cshtml");
        }

        [HttpPost]
        public async Task<ActionResult> Retirar(string cuenta, decimal monto)
        {
            var response = await client.PostAsync($"{baseUrl}/Retirar?cuenta={cuenta}&monto={monto}", null);
            ViewBag.Mensaje = await response.Content.ReadAsStringAsync();
            return View("~/Views/Banco/Retirar.cshtml");
        }

        [HttpPost]
        public async Task<ActionResult> Transferir(string cuentaOrigen, string cuentaDestino, decimal monto)
        {
            var response = await client.PostAsync($"{baseUrl}/Transferir?origen={cuentaOrigen}&destino={cuentaDestino}&monto={monto}", null);
            ViewBag.Mensaje = await response.Content.ReadAsStringAsync();
            return View("~/Views/Banco/Transferir.cshtml");
        }

        [HttpPost]
        public async Task<ActionResult> Saldo(string cuenta)
        {
            var response = await client.GetStringAsync($"{baseUrl}/Saldo?cuenta={cuenta}");
            ViewBag.Mensaje = response;
            return View("~/Views/Banco/Saldo.cshtml");
        }

        [HttpPost]
        public async Task<ActionResult> Movimientos(string cuenta)
        {
            var json = await client.GetStringAsync($"{baseUrl}/Movimientos?cuenta={cuenta}");
            var movimientos = JsonConvert.DeserializeObject<List<Movimiento>>(json);
            return View("~/Views/Banco/Movimientos.cshtml", movimientos);
        }
    }
}
