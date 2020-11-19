package com.chat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.chat.models.usuario;

@RestController
public class mensajesController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@MessageMapping("/chat/{para}")
	public void sendMessage(@DestinationVariable("para") String para, usuario mensajeUsuario) {
		
			simpMessagingTemplate.convertAndSend("/topic/messages/" + para, mensajeUsuario);
	}

}
