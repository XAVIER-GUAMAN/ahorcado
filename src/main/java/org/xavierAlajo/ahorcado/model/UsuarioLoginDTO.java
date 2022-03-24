package org.xavierAlajo.ahorcado.model;


import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;


@Entity
public class UsuarioLoginDTO {

	
	@NotNull 
	@NotBlank
	String usuario="";
	
	@NotNull 
	@NotBlank
	String clave="";

// -------- Constructor con todos los parametros -----------	
	public UsuarioLoginDTO(String usuario, String clave) {
		super();
		this.usuario = usuario;
		this.clave = clave;
	}
	

// -------- Constructor vacio -------
	public UsuarioLoginDTO() {
		super();
	}

	
	
//-------- Getter y Setter ----------
	public  String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
	



}
