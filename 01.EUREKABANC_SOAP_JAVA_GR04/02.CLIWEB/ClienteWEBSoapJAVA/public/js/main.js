const endpoint = "http://localhost:3000/api/soap";


function login() {
  const usuario = document.getElementById("usuario").value.trim();
  const clave = document.getElementById("clave").value.trim();
  if (usuario === "MONSTER" && clave === "MONSTER9") {
    document.getElementById("login").classList.add("oculto");
    document.getElementById("menu").classList.remove("oculto");
    document.getElementById("formularios").classList.remove("oculto");
  } else {
    alert("Credenciales inválidas");
  }
}

function logout() {
  document.location.reload();
}

function mostrarFormulario(nombre) {
  document.querySelectorAll(".formulario").forEach(f => f.style.display = "none");
  document.getElementById("form-" + nombre).style.display = "block";
}

// Crea el cuerpo SOAP válido con los tags <arg0>, <arg1>, etc.
function crearSOAPBody(accion, valores) {
  let body = `<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ws="http://ws.monster.edu.ec/">`;
  body += `<soapenv:Header/><soapenv:Body><ws:${accion}>`;
  valores.forEach((v, i) => {
    body += `<arg${i}>${v}</arg${i}>`;
  });
  body += `</ws:${accion}></soapenv:Body></soapenv:Envelope>`;
  return body;
}

async function enviarSOAP(accion, valores) {
  const xml = crearSOAPBody(accion, valores);
  const res = await fetch(endpoint, {
    method: "POST",
    headers: {
      "Content-Type": "text/xml"
    },
    body: xml
  });
  const texto = await res.text();
  const match = texto.match(/<return>(.*?)<\/return>/);
  const resultado = match ? match[1] : "Respuesta no válida";
  document.getElementById("resultado").textContent = `${accion.toUpperCase()}: ${resultado}`;
}

// Métodos
function verSaldo() {
  enviarSOAP("verSaldo", [document.getElementById("cuentaSaldo").value]);
}
function depositar() {
  enviarSOAP("depositar", [
    document.getElementById("cuentaDeposito").value,
    document.getElementById("montoDeposito").value
  ]);
}
function retirar() {
  enviarSOAP("retirar", [
    document.getElementById("cuentaRetiro").value,
    document.getElementById("montoRetiro").value
  ]);
}
function transferir() {
  enviarSOAP("transferir", [
    document.getElementById("cuentaOrigen").value,
    document.getElementById("cuentaDestino").value,
    document.getElementById("montoTransferencia").value
  ]);
}