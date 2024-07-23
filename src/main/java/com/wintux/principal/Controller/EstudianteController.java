package com.wintux.principal.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	
}
