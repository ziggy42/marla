const rp = require("request-promise")
const { API_ENDPOINT, TRACING_HEADERS } = require("./config")

const postJob = (job, headers) => rp({
    method: "POST",
    uri: `${API_ENDPOINT}/api/job`,
    json: true,
    body: job,
    headers: Object.keys(headers).filter((key) => TRACING_HEADERS.includes(key)).map((key) => headers[key])
})

module.exports = { postJob }