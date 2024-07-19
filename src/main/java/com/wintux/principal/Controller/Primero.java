package com.wintux.principal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Primero {
	@RequestMapping("/pregrado/estudiante")
	@ResponseBody
	public String obtenerNombre() {
		return "Pepe Perales";
	}
}
