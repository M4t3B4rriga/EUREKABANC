using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using ClienteConsolaSOAP.ServicioBancario;

namespace ClienteConsolaSOAP.ec_edu.monster.vista
{
    public class VistaMenu
    {
        public static void MostrarMenu()
        {
            var cliente = new ServicioBancarioSoapClient();

            while (true)
            {
                Console.WriteLine("\n===== MENÚ =====");
                Console.WriteLine("1. Depositar");
                Console.WriteLine("2. Retirar");
                Console.WriteLine("3. Transferir");
                Console.WriteLine("4. Consultar Saldo");
                Console.WriteLine("5. Consultar Movimientos");
                Console.WriteLine("6. Salir");
                Console.Write("Elige una opción: ");
                string opcion = Console.ReadLine();

                switch (opcion)
                {
                    case "1":
                        Console.Write("Cuenta: ");
                        string cuentaDep = Console.ReadLine();
                        Console.Write("Monto: ");
                        decimal montoDep = decimal.Parse(Console.ReadLine());
                        Console.WriteLine(cliente.Depositar(cuentaDep, montoDep));
                        break;

                    case "2":
                        Console.Write("Cuenta: ");
                        string cuentaRet = Console.ReadLine();
                        Console.Write("Monto: ");
                        decimal montoRet = decimal.Parse(Console.ReadLine());
                        Console.WriteLine(cliente.Retirar(cuentaRet, montoRet));
                        break;

                    case "3":
                        Console.Write("Cuenta Origen: ");
                        string cuentaOri = Console.ReadLine();
                        Console.Write("Cuenta Destino: ");
                        string cuentaDes = Console.ReadLine();
                        Console.Write("Monto: ");
                        decimal montoTrans = decimal.Parse(Console.ReadLine());
                        Console.WriteLine(cliente.Transferir(cuentaOri, cuentaDes, montoTrans));
                        break;

                    case "4":
                        Console.Write("Cuenta: ");
                        string cuentaSaldo = Console.ReadLine();
                        Console.WriteLine($"Saldo actual: {cliente.ConsultarSaldo(cuentaSaldo)}");
                        break;

                    case "5":
                        Console.Write("Cuenta: ");
                        string cuentaMov = Console.ReadLine();
                        var movimientos = cliente.ConsultarMovimientos(cuentaMov);
                        if (movimientos != null && movimientos.Tables.Count > 0)
                        {
                            var dt = movimientos.Tables[0];
                            var filas = dt.Select("", "dtt_movifecha DESC");
                            Console.WriteLine("Movimientos (más recientes primero):");
                            foreach (var fila in filas)
                            {
                                Console.WriteLine($"Fecha: {fila["dtt_movifecha"]}, Tipo: {fila["chr_tipocodigo"]}, Monto: {fila["dec_moviimporte"]}");
                            }
                        }
                        else
                        {
                            Console.WriteLine("No se encontraron movimientos.");
                        }
                        break;

                    case "6":
                        return;

                    default:
                        Console.WriteLine("Opción no válida.");
                        break;
                }
            }
        }
    }
}