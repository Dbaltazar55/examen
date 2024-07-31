package com.wintux.principal.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wintux.principal.Exceptions.EstudianteNoEncontradoException;
import com.wintux.principal.Models.Empleado;
import com.wintux.principal.Models.Estudiante;


@Controller
public class EmpleadoController {

	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Empleado> empleados = new HashMap<>();
	static {
		
		
		
		Empleado e1 = new Empleado("4920810", "jorge","lopez","76237951","jorge@gmail.com");
		Empleado e2 = new Empleado("7920810", "luis","gomez","78737951","lopez@gmail.com");
		Empleado e3 = new Empleado("8920810", "teresa","sanchez","85237951","sanchez@gmail.com");
		empleados.put("4920810", e1);
		empleados.put("7920810", e2);
		empleados.put("8920810", e3);
		
		
	}
	
	
	// http://localhost:9092/ex/empleado [GET]
		@GetMapping("/ex/empleado")
		public ResponseEntity<Object> getEmpleados(){
			return new ResponseEntity<>(empleados.values(), HttpStatus.OK); // 200
		}

		// http://localhost:9092/ex/empleado [POST]
		@PostMapping("/ex/empleado")
		public ResponseEntity<Object> nuevoEmpleado(@RequestBody Empleado emp){
			empleados.put(emp.getCi()+"", emp);
			URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(emp.getCi()).toUri();
			//return new ResponseEntity<>("Se crea al estudiante"+est.getId(), HttpStatus.CREATED); // 201
			return ResponseEntity.created(ubicacionDelRecurso).build();
		}	
		
		
		
}
