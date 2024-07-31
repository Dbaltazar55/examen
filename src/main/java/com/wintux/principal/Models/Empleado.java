package com.wintux.principal.Models;

public class Empleado {
	
	private String Ci;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    private String Email;

    public Empleado(String ci, String nombre, String apellido,  String telefono,  String email) {
		super();
		Ci = ci;
		Nombre = nombre;
		Apellido = apellido;
		Telefono = telefono;
		Email = email;
	}
    
    public String getCi() {
		return Ci;
	}
	public void setCi(String ci) {
		Ci = ci;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getTelefono() {
		return Telefono;
	}
	public void setTelefono(String telefono) {
		Telefono = telefono;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	
}






