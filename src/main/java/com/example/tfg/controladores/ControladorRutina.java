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
import com.example.tfg.modelos.Ejercicio;
import com.example.tfg.modelos.Rutina;
import com.example.tfg.repositorios.DiaRepository;
import com.example.tfg.repositorios.EjercicioRepository;
import com.example.tfg.repositorios.RutinaRepository;

@Controller
public class ControladorRutina {

	@Autowired
	private RutinaRepository repRut;
	@Autowired
	private DiaRepository repDia;
	@Autowired
	private EjercicioRepository repEj;
	
	@PostMapping("/rut")
	@ResponseBody Object postRut(@RequestBody Rutina rutina) {
		repRut.save(rutina);
		return rutina;
	}
	
	@GetMapping("/rut")
	@ResponseBody Object getRut(@RequestParam int idDia) {
		Iterable<Rutina> iterRut = repRut.findAll();
		List<Rutina> rutinas = new ArrayList<Rutina>();
		for(Rutina rutina : iterRut) {
			if(rutina.getDia().getId() == idDia) {
				rutinas.add(rutina);
			}
		}
		
		if(rutinas.size() < 1) {
			anadir(idDia);
		}
		return rutinas;
	}
	
	private void crear(int repeticiones, Dia dia, Ejercicio ejercicio) {
		Rutina rutina = new Rutina();
		rutina.setRepeticiones(repeticiones);
		rutina.setDia(dia);
		rutina.setEjercicio(ejercicio);
		postRut(rutina);
	}
	
	private void anadir(int idDia) {
		Iterable<Dia> iterDia = repDia.findAll();
		Iterable<Ejercicio> iterEj = repEj.findAll();
		for(Dia dia : iterDia) {
			if(dia.getId() == idDia) {
				if(dia.getSemana().getNombre().equals("facil") && (dia.getNombre().equals("Lunes") ||
						dia.getNombre().equals("Martes") ||
						dia.getNombre().equals("Miercoles") ||
						dia.getNombre().equals("Jueves") ||
						dia.getNombre().equals("Viernes"))) {
					for(Ejercicio ej : iterEj) {
						if(!ej.getNombre().equals("Descanso")) {
							crear(5,dia,ej);						
						}
					}
				}
				
				if(dia.getSemana().getNombre().equals("facil") && (dia.getNombre().equals("Sabado") ||
						dia.getNombre().equals("Domingo"))) {
					for(Ejercicio ej : iterEj) {
						if(ej.getNombre().equals("Descanso")) {
							crear(0,dia,ej);
						}
					}
				}
				if(dia.getSemana().getNombre().equals("medium") && (dia.getNombre().equals("Lunes") ||
						dia.getNombre().equals("Martes") ||
						dia.getNombre().equals("Miercoles") ||
						dia.getNombre().equals("Jueves") ||
						dia.getNombre().equals("Viernes"))) {
					for(Ejercicio ej : iterEj) {
						if(!ej.getNombre().equals("Descanso")) {
							crear(10,dia,ej);						
						}
					}
				}
				
				if(dia.getSemana().getNombre().equals("medium") && (dia.getNombre().equals("Sabado") ||
						dia.getNombre().equals("Domingo"))) {
					for(Ejercicio ej : iterEj) {
						if(ej.getNombre().equals("Descanso")) {
							crear(0,dia,ej);
						}
					}
				}
				
				if(dia.getSemana().getNombre().equals("dificil") && (dia.getNombre().equals("Lunes") ||
						dia.getNombre().equals("Martes") ||
						dia.getNombre().equals("Miercoles") ||
						dia.getNombre().equals("Jueves") ||
						dia.getNombre().equals("Viernes"))) {
					for(Ejercicio ej : iterEj) {
						if(!ej.getNombre().equals("Descanso")) {
							crear(15,dia,ej);						
						}
					}
				}
				
				if(dia.getSemana().getNombre().equals("dificil") && (dia.getNombre().equals("Sabado") ||
						dia.getNombre().equals("Domingo"))) {
					for(Ejercicio ej : iterEj) {
						if(ej.getNombre().equals("Descanso")) {
							crear(0,dia,ej);
						}
					}
				}
			}
		}
	}
}
