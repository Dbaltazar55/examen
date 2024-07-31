package com.wintux.principal.Models;

public class Cliente {
	private String nit;
	private String apellido;

	public Cliente(String nit, String apellido) {
		super();
		this.nit = nit;
		this.apellido = apellido;
	}
	
	public String getNit() {
		return nit;
	}
	public void setNit(String nit) {
		this.nit = nit;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
}
