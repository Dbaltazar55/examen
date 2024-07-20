package com.wintux.principal.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Primero {
	@Value("${valor.secreto}")
	private String val;
	@RequestMapping("/pregrado/estudiante")
	@ResponseBody
	public String obtenerNombre() {
		return "Pepe Perales: " + val;
	}
}
