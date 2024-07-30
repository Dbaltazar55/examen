package com.wintux.principal.Configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.wintux.principal.Interceptors.EstudianteInterceptor;

@Component
public class EstudianteInterceptorAppConfiguration implements WebMvcConfigurer{
	@Autowired
	private EstudianteInterceptor interceptor;
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(interceptor);
	}
}
