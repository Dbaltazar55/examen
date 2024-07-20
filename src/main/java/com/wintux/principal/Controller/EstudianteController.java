package com.wintux.principal.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/estudiante")
public class EstudianteController {
	@GetMapping // localhost:7000/estudiante [GET]
	public String index() {
		return "estudiante/index";
	}
}
