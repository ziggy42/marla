const express = require("express")
const pug = require("pug")
const { PORT, WS_ENDPOINT } = require("./config")
const { postJob } = require("./api")

const app = express()
app.use(express.json())

app.get("/", (req, res) => res.send(pug.renderFile(`${__dirname}/../public/index.pug`, { WS_ENDPOINT })))

app.post("/job", (req, res) => postJob(req.body, req.headers).then((response) => res.send(response)))

app.get("/health", (req, res) => res.send("ok"))

app.listen(PORT, () => console.log(`Frontend listening on port ${PORT}!`))