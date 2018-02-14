const express = require("express")
const rp = require("request-promise")
const pug = require("pug")

const PORT = process.env.PORT || 3000
const WS_ENDPOINT = process.env.WS_ENDPOINT || "ws://localhost:8090/stomp"
const API_ENDPOINT = process.env.API_ENDPOINT || "http://localhost:8080"

const app = express()
app.use(express.json())

app.get("/", (req, res) => res.send(pug.renderFile("public/index.pug", { WS_ENDPOINT })))

app.post("/job", (req, res) => rp({
    method: "POST",
    uri: `${API_ENDPOINT}/api/job`,
    json: true,
    body: req.body
}).then((response) => res.send(response)))

app.get("/health", (req, res) => res.send("ok"))

app.listen(PORT, () => console.log(`Frontend listening on port ${PORT}!`))