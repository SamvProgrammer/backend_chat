package com.chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class configuracionSocket implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	System.out.println("registerStompEndpoints");
        registry.addEndpoint("/conectar").setAllowedOrigins("http://localhost:4200").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
    	
        registry.setApplicationDestinationPrefixes("/entrada");
        registry.enableSimpleBroker("/topic");   
    }
}

