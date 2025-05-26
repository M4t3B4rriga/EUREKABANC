const express = require('express');
const axios = require('axios');
const cors = require('cors');
const bodyParser = require('body-parser');

const app = express();
const PORT = 3000;
const REST_SERVER = "http://10.40.26.183:8080/BancoRESTServidorJAVA/api";

app.use(cors());
app.use(bodyParser.json());
app.use(express.static("public"));

// Proxy de solicitudes al servidor REST
app.use("/api", async (req, res) => {
  const url = REST_SERVER + req.url;
  try {
    const response = await axios({
      method: req.method,
      url,
      data: req.body,
      headers: { "Content-Type": "application/json" }
    });
    res.status(response.status).json(response.data);
  } catch (error) {
    res.status(error.response?.status || 500).send(error.message);
  }
});

app.listen(PORT, () => {
  console.log(`Proxy en http://localhost:${PORT}`);
});
