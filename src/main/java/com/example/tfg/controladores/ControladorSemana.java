package com.example.tfg.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.Semana;
import com.example.tfg.repositorios.SemanaRepository;

@Controller
public class ControladorSemana {

	@Autowired
	private SemanaRepository repSem;
	
	@PostMapping("/semana")
	@ResponseBody Object postSemana(@RequestBody Semana semana) {
		repSem.save(semana);
		return semana;
	}
	
	@GetMapping("/semana")
	@ResponseBody Object getSemana() {
		
		if(repSem.findAll().size() < 1)
			anadir();
		return repSem.findAll();
	}
	
	private void crear(String nombre) {
		Semana semana = new Semana();
		semana.setNombre(nombre);
		postSemana(semana);
	}
	
	private void anadir() {
		crear("facil");
		crear("medium");
		crear("dificil");
	}
}
