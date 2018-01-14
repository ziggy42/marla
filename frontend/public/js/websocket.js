$(document).ready(() => {
    const messageList = $("#messages")
    const stompClient = Stomp.client("ws://localhost:8090/stomp")
    stompClient.connect({}, () => {
        stompClient.subscribe("/Andrea", (data) => messageList.append(`<li>${data.body}</li>`))
    })
})