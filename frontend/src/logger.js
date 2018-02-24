const { createLogger, transports, format } = require("winston")
const { simple } = format

module.exports = createLogger({
    format: simple(),
    transports: [new transports.Console()]
})