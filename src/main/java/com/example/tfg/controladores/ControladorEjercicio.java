package com.example.tfg.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.Ejercicio;
import com.example.tfg.repositorios.EjercicioRepository;

@Controller
public class ControladorEjercicio {

	@Autowired
	private EjercicioRepository repEj;
	
	@PostMapping("/ej")
	@ResponseBody Object postEj(@RequestBody Ejercicio ejercicio) {
		repEj.save(ejercicio);
		return ejercicio;
	}
}
