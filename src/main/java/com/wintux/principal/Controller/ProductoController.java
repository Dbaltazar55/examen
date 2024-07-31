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
//import com.wintux.principal.Models.Cliente;
//import com.wintux.principal.Models.Empleado;
//import com.wintux.principal.Models.Estudiante;
//import com.wintux.principal.Models.Estudiante;
import com.wintux.principal.Models.Estudiante;
import com.wintux.principal.Models.Producto;


@Controller
public class ProductoController {

	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Producto> productos = new HashMap<>();
	static {
		
		
		
		Producto e1 = new Producto(1,"lopez","lopez");
		Producto e2 = new Producto(2,"gomez","lopez");
		Producto e3 = new Producto(3,"sanchez","lopez");
		productos.put("1", e1);
		productos.put("2", e2);
		productos.put("3", e3);
		
    	}
	
	
		
	// http://localhost:9092/ex/producto [GET]
		@GetMapping("/ex/producto")
		public ResponseEntity<Object> getProducto(){
			return new ResponseEntity<>(productos.values(), HttpStatus.OK); // 200
		}

		// http://localhost:9092/ex/producto [POST]
		@PostMapping("/ex/producto")
		public ResponseEntity<Object> nuevoProducto(@RequestBody Producto pro){
			productos.put(pro.getCodigo()+"", pro);
			URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(pro.getCodigo()).toUri();
			//return new ResponseEntity<>("Se crea al empleo"+est.getCi(), HttpStatus.CREATED); // 201
			return ResponseEntity.created(ubicacionDelRecurso).build();
		}	
		
		
		// http://localhost:9092/ex/producto/4920810014 [PUT]
				@PutMapping("/ex/producto/{identif}")
				public ResponseEntity<Object> modificarProducto(@PathVariable("identif") String iidd, @RequestBody Producto pro ){
					if(!productos.containsKey(iidd))
						throw new EstudianteNoEncontradoException();
					productos.remove(iidd);
					pro.setCodigo(Integer.parseInt(iidd));
					productos.put(iidd, pro);
					return new ResponseEntity<>("Se modifica el producto "+pro.getCodigo(), HttpStatus.OK); // 201
				}
		
					
				
		// http://localhost:9092/ex/cliente/4920810014 [DELETE]
		@DeleteMapping("/ex/cliente/{identif}")
		public ResponseEntity<Object> eliminarProducto(@PathVariable("identif") String iidd){
			productos.remove(iidd);
			return new ResponseEntity<>("Se elimina el producto "+iidd, HttpStatus.OK); // 200
		}
	
		
		
}