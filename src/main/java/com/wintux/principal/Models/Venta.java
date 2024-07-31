package com.wintux.principal.Models;

public class Venta {

	private int id;
	private String emp;
	private String clien;
	private String prod;
	private String cantidad;
	
	public Venta(int id, String emp, String clien,String prod, String cantidad) {
		super();
		this.id = id;
		this.emp = emp;
		this.clien = clien;
		this.prod = prod;
		this.cantidad = cantidad;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmp() {
		return emp;
	}
	public void setEmp(String emp) {
		this.emp = emp;
	}
	public String getClien() {
		return clien;
	}
	public void setClien(String clien) {
		this.clien = clien;
	}
	public String getPro() {
		return prod;
	}
	public void setPro(String pro) {
		this.prod = pro;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
}
