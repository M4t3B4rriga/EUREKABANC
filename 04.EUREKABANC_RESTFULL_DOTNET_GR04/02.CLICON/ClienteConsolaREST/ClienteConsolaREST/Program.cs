using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using ClienteConsolaREST.ec.edu.monster.vista;

internal class Program
{
    static async Task Main(string[] args)
    {
        var menu = new Menu();
        await menu.MostrarMenu();
    }
}