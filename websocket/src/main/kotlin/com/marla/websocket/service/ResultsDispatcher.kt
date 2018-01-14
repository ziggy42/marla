package com.marla.websocket.service

import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class ResultsDispatcher(
    private val resultListenerService: ResultListenerService,
    private val webSocket: SimpMessagingTemplate
) {

    fun start() {
        generateSequence { resultListenerService.getResult() }.forEach {
            webSocket.convertAndSend("/${it.clientId}", it.value!!)
        }
    }
}