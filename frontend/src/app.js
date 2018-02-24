const express = require("express")
const pug = require("pug")
const { PORT, WS_ENDPOINT } = require("./config")
const { postJob } = require("./api")
const logger = require("./logger")

const app = express()
app.use(express.json())

app.get("/", (req, res) => res.send(pug.renderFile(`${__dirname}/../public/index.pug`, { WS_ENDPOINT })))

app.post("/job", (req, res) => {
    logger.debug("Posting new job")
    postJob(req.body, req.headers)
        .then((response) => {
            logger.debug(`Api response: ${response}`)
            res.send(response)
        })
        .catch((err) => {
            logger.error(`Error posting job: ${err.message}`)
            res.status(500).send(err.message)
        })
})

app.get("/health", (req, res) => res.send("ok"))

app.listen(PORT, () => logger.info(`Frontend listening on port ${PORT}!`))