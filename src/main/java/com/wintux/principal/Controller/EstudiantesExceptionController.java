package com.wintux.principal.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wintux.principal.Exceptions.EstudianteNoEncontradoException;

@ControllerAdvice
public class EstudiantesExceptionController {
	@ExceptionHandler(value=EstudianteNoEncontradoException.class)
	public ResponseEntity<Object> unaExcepcion(EstudianteNoEncontradoException ex){
		return new ResponseEntity<>("No se encontro al estudiante", HttpStatus.NOT_FOUND);
	}
}
