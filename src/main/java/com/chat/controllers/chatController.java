package com.chat.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.chat.Service.usuarioService;
import com.chat.models.usuario;

@RestController
@RequestMapping("/api")
@SessionScope
public class chatController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -758126060067618563L;


	@Autowired
	private usuarioService obj;

	
	@Autowired
	private List<usuario> listaUsuarios;

	@GetMapping("/usuario")
	public usuario getUsuario() {

		boolean encontrado = false;
		for(usuario item : listaUsuarios) {
			   if(item.getNickname().contains(obj.getUsuario().getNickname())) {
				   encontrado = true;
				   break;
			   }
		}
		
		if(!encontrado) {
			listaUsuarios.add(obj.getUsuario());
		}
				

		return obj.getUsuario();

	}

	@GetMapping("/getTotalUsers")
	public Integer getTotalUsers() {

		return listaUsuarios.size();

	}
	
	
	@GetMapping("/listausuarios")
	public List<usuario> listaUsuarios(){
		return listaUsuarios;
	}
	
	
	@PostMapping("/usuario")
	public Map<String,Object> cambiarNombreUsuario(@RequestBody usuario user) {
		boolean repetido = false; 
		usuario userencontrado = null;
		for(usuario item:this.listaUsuarios) {
			  
			 if(!item.getNickname().contains(user.getCambiara())) {
				 if(item.getNickname().contains(user.getNickname())) {
					  userencontrado = item;					  
				  }
			 }else {
				 
				 repetido = true;
				 break;
				 
			 }
			  
			  
		 }
		String mensajeStr = "Usuario Repetido";
		boolean afectado = false;
		if(!repetido) {
			userencontrado.setNickname(user.getCambiara());
			mensajeStr = "Usuario cambiado correctamente";
			afectado = true;
		}
		
		Map<String,Object> mensaje = new HashMap<String,Object>();
		mensaje.put("respuesta", mensajeStr);
		mensaje.put("afectado", afectado);
		return  mensaje;
	}
	
	
	@PostConstruct
	public void construir() {
	}
	
	
	@PreDestroy
	public void eliminar() {
	
		System.out.println("Esto cuando se va la peticion");
		for(usuario item : listaUsuarios) {
			   if(item.getNickname().contains(obj.getUsuario().getNickname())) {
				   listaUsuarios.remove(item);
				   break;
			   }
		}
	}

}
