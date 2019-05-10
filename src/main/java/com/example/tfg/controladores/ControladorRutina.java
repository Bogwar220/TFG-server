package com.example.tfg.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.Rutina;
import com.example.tfg.repositorios.RutinaRepository;

@Controller
public class ControladorRutina {

	@Autowired
	private RutinaRepository repRut;
	
	@PostMapping("/rut")
	@ResponseBody Object postRut(@RequestBody Rutina rutina) {
		repRut.save(rutina);
		return rutina;
	}
}
