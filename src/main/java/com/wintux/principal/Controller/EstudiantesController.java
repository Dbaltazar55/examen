package com.wintux.principal.Controller;

import java.lang.reflect.Field;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.wintux.principal.Exceptions.EstudianteNoEncontradoException;
import com.wintux.principal.Models.Estudiante;

@Controller
public class EstudiantesController {
	@Autowired
	ObjectMapper objectMapper;
	
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
		if(!estudiantes.containsKey(iidd))
			throw new EstudianteNoEncontradoException();
		estudiantes.remove(iidd);
		est.setId(Integer.parseInt(iidd));
		estudiantes.put(iidd, est);
		return new ResponseEntity<>("Se modifica al estudiante "+est.getId(), HttpStatus.OK); // 201
	}
	// http://localhost:7000/pre/estudiantes/4 [DELETE]
	@DeleteMapping("/pre/estudiantes/{identif}")
	public ResponseEntity<Object> eliminarEstudiante(@PathVariable("identif") String iidd){
		estudiantes.remove(iidd);
		return new ResponseEntity<>("Se elimina al estudiante "+iidd, HttpStatus.OK); // 200
	}
	// http://localhost:7000/pre/estudiantes/4 [PATCH]
		@PatchMapping("/pre/estudiantes/{identif}")
		public ResponseEntity<Object> modificar2Estudiante(@PathVariable("identif") String iidd, @RequestBody Map<String,Object> atributosModificados){
			Estudiante estOriginal = estudiantes.get(iidd);
			atributosModificados.forEach((atributo,valorNuevo)-> {
				Field campo = ReflectionUtils.findField(Estudiante.class, atributo);
				if(campo != null) {
					campo.setAccessible(true);
					ReflectionUtils.setField(campo, estOriginal, valorNuevo);
				}
			});
			estudiantes.remove(iidd);
			estudiantes.put(iidd, estOriginal);
			return new ResponseEntity<>("Se modifica (PATCH) al estudiante "+iidd, HttpStatus.OK); // 200
		}
		// http://localhost:7000/pre/estudiantes/patch/4 [PATCH] JSON
		@PatchMapping(path="/pre/estudiantes/patch/{identif}", consumes="application/json-patch+json")
		public ResponseEntity<Object> modificar3Estudiante(@PathVariable("identif") String iidd, @RequestBody JsonPatch atributosModificados){
				try {
					Estudiante estOriginal = estudiantes.get(iidd);
					JsonNode patcheado = atributosModificados.apply(
							objectMapper.convertValue(estOriginal, JsonNode.class));
					Estudiante estActualizado = 
							objectMapper.treeToValue(patcheado, Estudiante.class);
					estudiantes.remove(iidd);
					estudiantes.put(iidd, estActualizado);
					return new ResponseEntity<>("Se modifica ( JSON PATCH) al estudiante "+iidd, HttpStatus.OK); // 200
				}catch(Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>("Error fatal al modificar al estudiante " + iidd,HttpStatus.INTERNAL_SERVER_ERROR); // 500
				}	
					
		}
		// Ejemplo de headers
		// http://localhost:7000/pre/estudiantes/status [GET]
		@GetMapping("/pre/estudiantes/status")
		public ResponseEntity<String> customHeaders(){
			HttpHeaders headers = new HttpHeaders();
			headers.add("Modo-Universidad", "CRISIS");
			headers.add("Permiso-Cifrado", "true");
			headers.add("Cantidad-est", "248");
			return new ResponseEntity<>("Respuesta con cabeceras propias",headers,HttpStatus.OK);
		}
		
		// http://localhost:7000/pre/estudiantes/edad?n=2027 [GET]
				@GetMapping("/pre/estudiantes/edad")
				public ResponseEntity<String> edad(@RequestParam("n") int e){
					if(estaEnElFuturo(e))
						return ResponseEntity.badRequest().body("El año no puede ser en el futuro");
					return ResponseEntity.status(HttpStatus.OK).body("La edad es de "+ calcularEdad(e));
					
				}
				private boolean estaEnElFuturo(int year) {
					return year > java.time.Year.now().getValue();
				}
				private int calcularEdad(int year) {
					return java.time.Year.now().getValue() - year;
				}
}
