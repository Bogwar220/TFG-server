package com.example.tfg.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.Dia;
import com.example.tfg.modelos.Semana;
import com.example.tfg.modelos.SemanaUser;
import com.example.tfg.repositorios.DiaRepository;
import com.example.tfg.repositorios.SemanaRepository;
import com.example.tfg.repositorios.SemanaUserRepository;

@Controller
public class ControladorDia {

	@Autowired
	private DiaRepository repDia;
	@Autowired
	private SemanaRepository repSem;
	@Autowired
	private SemanaUserRepository repSemUser;
	
	@PostMapping("/dia")
	@ResponseBody Object postDia(@RequestBody Dia dia) {
		repDia.save(dia);
		return dia;
	}
	
	@GetMapping("/dia")
	@ResponseBody Object getDia(@RequestParam int idUser, @RequestParam int idSem) {
		Iterable<SemanaUser> iterSemUser = repSemUser.findAll();
		Iterable<Dia> iterDia = repDia.findAll();
		List<Dia> dias = new ArrayList<Dia>();
		for(SemanaUser semUser : iterSemUser) {
			if(semUser.getUser().getId() == idUser && semUser.getSemana().getId() == idSem) {
				for(Dia dia : iterDia) {
					if(dia.getSemana().getId() == semUser.getSemana().getId()) {
						dias.add(dia);
					}
				}
			}
		}
		if(dias.size() < 1) {
			anadir();
		}
		
		return dias;
	}
	
	private void crear(String nombre, Semana semana) {
		Dia dia = new Dia();
		dia.setNombre(nombre);
		dia.setSemana(semana);
		postDia(dia);
	}
	
	private void anadir() {
		Iterable<Semana> iterSem = repSem.findAll();
		for(Semana semana : iterSem) {
			if(semana.getNombre().equals("facil") || 
					semana.getNombre().equals("medium") ||
					semana.getNombre().equals("dificil")) {
				crear("Lunes", semana);
				crear("Martes", semana);
				crear("Miercoles", semana);
				crear("Jueves", semana);
				crear("Viernes", semana);
				crear("Sabado", semana);
				crear("Domingo", semana);
			}
		}
	}
}