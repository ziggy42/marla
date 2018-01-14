const express = require("express")
const app = express()

app.use(express.static("public"))

app.get("/health", (req, res) => res.send("ok"))

app.listen(3000, () => console.log("Frontend listening on port 3000!"))