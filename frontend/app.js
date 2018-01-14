const express = require("express")
const pug = require("pug")

const PORT = process.env.PORT || 3000
const WS_ENDPOINT = process.env.WS_ENDPOINT || "ws://localhost:8090/stomp"
const API_ENDPOINT = process.env.API_ENDPOINT || "http://localhost:8080/api/job"

const app = express()

app.get("/", (req, res) => res.send(pug.renderFile("public/index.pug", {
    WS_ENDPOINT, API_ENDPOINT
})))

app.get("/health", (req, res) => res.send("ok"))

app.listen(PORT, () => console.log(`Frontend listening on port ${PORT}!`))