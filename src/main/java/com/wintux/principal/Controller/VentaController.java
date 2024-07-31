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
import com.wintux.principal.Models.Venta;

@Controller
public class VentaController {
	@Autowired
	ObjectMapper objectMapper;
	
	private static Map<String, Venta> ventas = new HashMap<>();
	static {
		Venta e1 = new Venta(1, "Pepe","jorge","galletas","10");
		Venta e2 = new Venta(2, "Ana","Judas","lacteos","5");
		Venta e3 = new Venta(3, "Sofia","Raymundo","jugos","3");
		ventas.put("1", e1);
		ventas.put("2", e2);
		ventas.put("3", e3);
	}
	
	// http://localhost:7000/pre/ventas [GET]
	@GetMapping("/ex/ventas")
	public ResponseEntity<Object> getEstudiantes(){
		return new ResponseEntity<>(ventas.values(), HttpStatus.OK); // 200
	}
	// http://localhost:7000/pre/ventas [POST]
	@PostMapping("/ex/ventas")
	public ResponseEntity<Object> nuevoEstudiante(@RequestBody Venta ven){
		ventas.put(ven.getId()+"", ven);
		URI ubicacionDelRecurso = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(ven.getId()).toUri();
		//return new ResponseEntity<>("Se crea al estudiante"+est.getId(), HttpStatus.CREATED); // 201
		return ResponseEntity.created(ubicacionDelRecurso).build();
	}
	// http://localhost:7000/pre/estudiantes/4 [PUT]
	@PutMapping("/ex/ventas/{identif}")
	public ResponseEntity<Object> modificarEstudiante(@PathVariable("identif") String iidd, @RequestBody Venta ven ){
		if(!ventas.containsKey(iidd))
			//throw new EstudianteNoEncontradoException();
		ventas.remove(iidd);
		ven.setId(Integer.parseInt(iidd));
		ventas.put(iidd, ven);
		return new ResponseEntity<>("Se modifica al estudiante "+ven.getId(), HttpStatus.OK); // 201
	}
	// http://localhost:7000/pre/estudiantes/4 [DELETE]
	@DeleteMapping("/ex/ventas/{identif}")
	public ResponseEntity<Object> eliminarEstudiante(@PathVariable("identif") String iidd){
		ventas.remove(iidd);
		return new ResponseEntity<>("Se elimina al estudiante "+iidd, HttpStatus.OK); // 200
	}
}