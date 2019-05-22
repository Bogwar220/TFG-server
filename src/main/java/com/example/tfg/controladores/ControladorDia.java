package com.example.tfg.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.Dia;
import com.example.tfg.modelos.Semana;
import com.example.tfg.modelos.SemanaUser;
import com.example.tfg.modelos.User;
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
	@ResponseBody Object getDia(@RequestParam int idSemUser) {
		Iterable<SemanaUser> iterSemUser = repSemUser.findAll();
		Iterable<Dia> iterDia = repDia.findAll();
		List<Dia> dias = new ArrayList<Dia>();
		for(SemanaUser semUser : iterSemUser) {
			if(semUser.getId() == idSemUser) {
				for(Dia dia : iterDia) {
					if(semUser.getSemana().getId() == dia.getSemana().getId()) {
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
			if(semana.getNombre().equals("Facil") || 
					semana.getNombre().equals("Medium") ||
					semana.getNombre().equals("Dificil")) {
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
	
	@PutMapping("/dia")
	@ResponseBody Object putDia(@RequestBody Dia dia) {
		Iterable<Dia> iterDia = repDia.findAll();
		for(Dia newDia : iterDia) {
			if(newDia.getId() == dia.getId()) {
				if(dia.getNombre() != null) {
					newDia.setNombre(dia.getNombre());
					repDia.save(newDia);
					return newDia;
				}				
			}
		}
		return null;
	}
}
