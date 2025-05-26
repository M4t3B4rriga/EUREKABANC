const API = "http://localhost:3000/api/cuenta";

async function enviarPost(url, data, resultadoId) {
  const res = await fetch(url, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data)
  });
  const text = await res.text();
  document.getElementById(resultadoId || "resultado").textContent = text;
}

async function depositar() {
  const cuenta = document.getElementById("cuenta").value;
  const monto = parseFloat(document.getElementById("monto").value);
  enviarPost(`${API}/depositar`, { codigo: cuenta, saldo: monto });
}

async function retirar() {
  const cuenta = document.getElementById("cuenta").value;
  const monto = parseFloat(document.getElementById("monto").value);
  enviarPost(`${API}/retirar`, { codigo: cuenta, saldo: monto });
}

async function transferir() {
  const origen = document.getElementById("origen").value;
  const destino = document.getElementById("destino").value;
  const monto = parseFloat(document.getElementById("monto").value);
  const res = await fetch(`${API}/transferir?origen=${origen}&destino=${destino}&monto=${monto}`, {
    method: "POST"
  });
  const text = await res.text();
  document.getElementById("resultado").textContent = text;
}

async function verSaldo() {
  const cuenta = document.getElementById("cuenta").value;
  const res = await fetch(`${API}/saldo/${cuenta}`);
  const data = await res.json();
  document.getElementById("resultado").textContent = "Saldo: $" + data.saldo;
}
