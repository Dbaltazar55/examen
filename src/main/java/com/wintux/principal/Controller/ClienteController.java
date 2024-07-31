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
import com.wintux.principal.Models.Cliente;



@Controller
public class ClienteController {

	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Cliente> clientes = new HashMap<>();
	static {
		
		
		
		Cliente e1 = new Cliente("4920810014","lopez");
		Cliente e2 = new Cliente("7920810015","gomez");
		Cliente e3 = new Cliente("8920810016","sanchez");
		clientes.put("4920810014", e1);
		clientes.put("7920810015", e2);
		clientes.put("8920810016", e3);
		
    	}
	
		
	// http://localhost:9092/ex/cliente [GET]
		@GetMapping("/ex/cliente")
		public ResponseEntity<Object> getCliente(){
			return new ResponseEntity<>(clientes.values(), HttpStatus.OK); // 200
		}

		// http://localhost:9092/ex/cliente [POST]
		@PostMapping("/ex/cliente")
		public ResponseEntity<Object> nuevoCliente(@RequestBody Cliente cli){
			clientes.put(cli.getNit()+"", cli);
			URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(cli.getNit()).toUri();
			//return new ResponseEntity<>("Se crea al empleo"+est.getCi(), HttpStatus.CREATED); // 201
			return ResponseEntity.created(ubicacionDelRecurso).build();
		}	
		
		
		// http://localhost:9092/ex/cliente/4920810014 [PUT]
				@PutMapping("/ex/cliente/{identif}")
				public ResponseEntity<Object> modificarCliente(@PathVariable("identif") String iidd, @RequestBody Cliente cli ){
					if(!clientes.containsKey(iidd))
						throw new EstudianteNoEncontradoException();
					clientes.remove(iidd);
					cli.setNit(iidd);
					clientes.put(iidd, cli);
					return new ResponseEntity<>("Se modifica al estudiante "+cli.getNit(), HttpStatus.OK); // 201
				}
		
		// http://localhost:9092/ex/cliente/4920810014 [DELETE]
		@DeleteMapping("/ex/cliente/{identif}")
		public ResponseEntity<Object> eliminarCliente(@PathVariable("identif") String iidd){
			clientes.remove(iidd);
			return new ResponseEntity<>("Se elimina al estudiante "+iidd, HttpStatus.OK); // 200
		}
	
		
		
}