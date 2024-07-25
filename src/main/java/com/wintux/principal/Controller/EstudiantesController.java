package com.wintux.principal.Controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

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

import com.wintux.principal.Models.Estudiante;

@Controller
public class EstudiantesController {
	private static Map<String, Estudiante> estudiantes = new HashMap<>();
	static {
		Estudiante e1 = new Estudiante(1, "Pepe","Perales");
		Estudiante e2 = new Estudiante(2, "Ana","Sosa");
		Estudiante e3 = new Estudiante(3, "Sofia","Rocha");
		estudiantes.put("1", e1);
		estudiantes.put("2", e2);
		estudiantes.put("3", e3);
	}
	
	// http://localhost:7000/pre/estudiantes [GET]
	@GetMapping("/pre/estudiantes")
	public ResponseEntity<Object> getEstudiantes(){
		return new ResponseEntity<>(estudiantes.values(), HttpStatus.OK); // 200
	}
	// http://localhost:7000/pre/estudiantes [POST]
	@PostMapping("/pre/estudiantes")
	public ResponseEntity<Object> nuevoEstudiante(@RequestBody Estudiante est){
		estudiantes.put(est.getId()+"", est);
		URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(est.getId()).toUri();
		//return new ResponseEntity<>("Se crea al estudiante"+est.getId(), HttpStatus.CREATED); // 201
		return ResponseEntity.created(ubicacionDelRecurso).build();
	}
	// http://localhost:7000/pre/estudiantes/4 [PUT]
	@PutMapping("/pre/estudiantes/{identif}")
	public ResponseEntity<Object> modificarEstudiante(@PathVariable("identif") String iidd, @RequestBody Estudiante est ){
		estudiantes.remove(iidd);
		est.setId(Integer.parseInt(iidd));
		estudiantes.put(iidd, est);
		return new ResponseEntity<>("Se modifica al estudiante "+est.getId(), HttpStatus.OK); // 201
	}
	// http://localhost:7000/pre/estudiantes/4 [PUT]
	@DeleteMapping("/pre/estudiantes/{identif}")
	public ResponseEntity<Object> eliminarEstudiante(@PathVariable("identif") String iidd){
		estudiantes.remove(iidd);
		return new ResponseEntity<>("Se elimina al estudiante "+iidd, HttpStatus.OK); // 201
	} 
}
