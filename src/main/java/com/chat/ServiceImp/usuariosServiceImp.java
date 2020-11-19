package com.chat.ServiceImp;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import com.chat.Service.usuarioService;
import com.chat.models.usuario;

@Service
@SessionScope
public class usuariosServiceImp implements usuarioService, Serializable {

	private static final long serialVersionUID = -6197086584089679418L;

	private usuario user;

	@Autowired
	@Qualifier("nickname")
	private String nickname;


	@Override
	public usuario getUsuario() {
		user = new usuario();

		user.setNickname(nickname);
		return user;
	}
	
	

	
	

}
