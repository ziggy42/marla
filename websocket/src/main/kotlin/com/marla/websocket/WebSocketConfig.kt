package com.marla.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : AbstractWebSocketMessageBrokerConfigurer() {

    override fun registerStompEndpoints(registry: StompEndpointRegistry?) {
        registry?.addEndpoint("/stomp")?.setAllowedOrigins("*")
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry?) {
        registry?.setApplicationDestinationPrefixes("/app")
    }
}
