using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace ClienteWebSOAP.ec.edu.monster.controlador
{
    public class LoginController : Controller
    {
        public ActionResult Index() => View();

        [HttpPost]
        public ActionResult Index(string usuario, string clave)
        {
            if (usuario == "MONSTER" && clave == "MONSTER9")
            {
                // Autenticación básica (no persistente)
                Session["Usuario"] = usuario;
                return RedirectToAction("Index", "Banco");
            }
            else
            {
                ViewBag.Error = "Credenciales incorrectas. Inténtalo nuevamente.";
                return View();
            }
        }

        public ActionResult Salir()
        {
            Session.Clear();
            return RedirectToAction("Index", "Login");
        }
    }
}