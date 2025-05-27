using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using ClienteWebSOAP.ServicioBancario;

namespace ClienteWebSOAP.ec.edu.monster.controlador
{
    public class BancoController : Controller
    {
        ServicioBancarioSoapClient cliente = new ServicioBancarioSoapClient();

        // Menú principal
        public ActionResult Index()
        {
            if (Session["Usuario"] == null)
                return RedirectToAction("Index", "Login");

            return View();
        }

        // Depositar
        public ActionResult Depositar() => View();

        [HttpPost]
        public ActionResult Depositar(string cuenta, decimal monto)
        {
            ViewBag.Mensaje = cliente.Depositar(cuenta, monto);
            return View();
        }

        // Retirar
        public ActionResult Retirar() => View();

        [HttpPost]
        public ActionResult Retirar(string cuenta, decimal monto)
        {
            ViewBag.Mensaje = cliente.Retirar(cuenta, monto);
            return View();
        }

        // Transferir
        public ActionResult Transferir() => View();

        [HttpPost]
        public ActionResult Transferir(string cuentaOrigen, string cuentaDestino, decimal monto)
        {
            ViewBag.Mensaje = cliente.Transferir(cuentaOrigen, cuentaDestino, monto);
            return View();
        }

        // Saldo
        public ActionResult Saldo() => View();

        [HttpPost]
        public ActionResult Saldo(string cuenta)
        {
            var saldo = cliente.ConsultarSaldo(cuenta);
            ViewBag.Mensaje = $"El saldo de la cuenta {cuenta} es: {saldo}";
            return View();
        }

        // Movimientos
        public ActionResult Movimientos() => View();

        [HttpPost]
        public ActionResult Movimientos(string cuenta)
        {
            var movimientos = cliente.ConsultarMovimientos(cuenta);
            ViewBag.Movimientos = movimientos?.Tables[0];
            return View();
        }
    }
}