using ClienteConsolaREST.ec.edu.monster.controlador;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ClienteConsolaREST.ec.edu.monster.vista
{
    public class Menu
    {
        private BancoController controlador = new BancoController();

        public async Task MostrarMenu()
        {
            if (!RealizarLogin())
            {
                Console.WriteLine("Acceso denegado.");
                return;
            }

            while (true)
            {
                Console.Clear();
                Console.WriteLine("=== Menú Principal - UrekaBank ===");
                Console.WriteLine("1. Depositar");
                Console.WriteLine("2. Retirar");
                Console.WriteLine("3. Transferir");
                Console.WriteLine("4. Consultar Saldo");
                Console.WriteLine("5. Consultar Movimientos");
                Console.WriteLine("0. Salir");
                Console.Write("Elige una opción: ");

                var opcion = Console.ReadLine();
                switch (opcion)
                {
                    case "1":
                        await Depositar();
                        break;
                    case "2":
                        await Retirar();
                        break;
                    case "3":
                        await Transferir();
                        break;
                    case "4":
                        await ConsultarSaldo();
                        break;
                    case "5":
                        await ConsultarMovimientos();
                        break;
                    case "0":
                        return;
                    default:
                        Console.WriteLine("Opción no válida.");
                        break;
                }
                Console.WriteLine("\nPresiona una tecla para continuar...");
                Console.ReadKey();
            }
        }

        private bool RealizarLogin()
        {
            Console.Clear();
            Console.WriteLine("=== LOGIN - UrekaBank ===");

            Console.Write("Usuario: ");
            string usuario = Console.ReadLine();

            Console.Write("Contraseña: ");
            string contrasena = Console.ReadLine();

            if (usuario.ToUpper() == "MONSTER" && contrasena == "MONSTER9")
            {
                Console.WriteLine("\n¡Acceso permitido! Bienvenido al sistema.");
                return true;
            }
            else
            {
                Console.WriteLine("\nCredenciales inválidas.");
                return false;
            }
        }

        private async Task Depositar()
        {
            Console.Write("Cuenta: ");
            var cuenta = Console.ReadLine();
            Console.Write("Monto: ");
            var monto = decimal.Parse(Console.ReadLine());
            Console.WriteLine(await controlador.Depositar(cuenta, monto));
        }

        private async Task Retirar()
        {
            Console.Write("Cuenta: ");
            var cuenta = Console.ReadLine();
            Console.Write("Monto: ");
            var monto = decimal.Parse(Console.ReadLine());
            Console.WriteLine(await controlador.Retirar(cuenta, monto));
        }

        private async Task Transferir()
        {
            Console.Write("Cuenta Origen: ");
            var origen = Console.ReadLine();
            Console.Write("Cuenta Destino: ");
            var destino = Console.ReadLine();
            Console.Write("Monto: ");
            var monto = decimal.Parse(Console.ReadLine());
            Console.WriteLine(await controlador.Transferir(origen, destino, monto));
        }

        private async Task ConsultarSaldo()
        {
            Console.Write("Cuenta: ");
            var cuenta = Console.ReadLine();
            Console.WriteLine(await controlador.ConsultarSaldo(cuenta));
        }

        private async Task ConsultarMovimientos()
        {
            Console.Write("Cuenta: ");
            var cuenta = Console.ReadLine();
            var movimientos = await controlador.ConsultarMovimientos(cuenta);
            foreach (var m in movimientos)
            {
                Console.WriteLine($"{m.dtt_movifecha} | {m.dec_moviimporte} | {m.chr_tipocodigo}");
            }
        }
    }
}