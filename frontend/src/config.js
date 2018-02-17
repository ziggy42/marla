module.exports = {
    PORT: process.env.PORT || 3000,
    WS_ENDPOINT: process.env.WS_ENDPOINT || "ws://localhost:8090/stomp",
    API_ENDPOINT: process.env.API_ENDPOINT || "http://localhost:8080",
    TRACING_HEADERS: [
        "x-request-id",
        "x-b3-traceid",
        "x-b3-spanid",
        "x-b3-parentspanid",
        "x-b3-sampled",
        "x-b3-flags",
        "x-ot-span-context"
    ]
}