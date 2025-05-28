const express = require("express");
const bodyParser = require("body-parser");

const cors = require("cors");
const axios = require("axios");

const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.text({ type: "*/*" }));

// Proxy que reenvía el XML SOAP
app.post("/api/soap", async (req, res) => {
  try {
    const response = await axios.post(
      "http://10.40.25.131:8080/BancoSOAPServidorJAVA/BancoWSService",
      req.body,
      {
        headers: {
          "Content-Type": "text/xml;charset=UTF-8",
          "SOAPAction": ""
        }
      }
    );
    res.send(response.data);
  } catch (error) {
    res.status(500).send("Error en el proxy: " + error.message);
  }
});

// Servir archivos estáticos
app.use(express.static("public"));

app.listen(PORT, () => {
  console.log(`Servidor Proxy corriendo en http://localhost:${PORT}`);
});