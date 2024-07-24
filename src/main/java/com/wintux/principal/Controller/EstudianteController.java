package com.wintux.principal.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wintux.principal.Models.Persona;

@Controller
@RequestMapping("/estudiante")
public class EstudianteController {
	
	private static final Logger logger = LoggerFactory.getLogger(EstudianteController.class);
	 // localhost:7000/estudiante [GET]
	@GetMapping
	@ResponseBody
	public String index() {
		logger.info("Se acaba de llamar al controlador estudiante por GET.");
		
		if(5%2 != 0)
			logger.error("Se acaba de verificar que la division de 5 entre 2 no da resto 0.");
		return "estudiante/index"; // "estudiante/index"
		
		// fatal, error, warn, info, debug, trace
		
		// TODO: agregar IP de cliente a logs
		
		
		
	}
	// localhost:7000/estudiante/{matr} [GET]
	@GetMapping("/{matr}")
	@ResponseBody
	public void pruebaGetConParamURL(@PathVariable("matr") String a) {
		System.out.printf("Se alcanza metodo GET con parametro: %s", a);
	}
	
	// localhost:7000/estudiante [POST]
	@PostMapping
	@ResponseBody
	public String pruebaPost(@RequestBody Persona p) {
		System.out.printf("Nombre completo: %s %s, con edad: %d", p.getNombre(), p.getApellido(), p.getEdad());
		return "si";
	}
	
	
}
