const express = require("express")
const app = express()

const PORT = process.env.PORT || 3000

app.use(express.static("public"))

app.get("/health", (req, res) => res.send("ok"))

app.listen(PORT, () => console.log(`Frontend listening on port ${PORT}!`))