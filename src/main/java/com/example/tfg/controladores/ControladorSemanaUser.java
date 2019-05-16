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

import com.example.tfg.modelos.Semana;
import com.example.tfg.modelos.SemanaUser;
import com.example.tfg.modelos.User;
import com.example.tfg.repositorios.SemanaRepository;
import com.example.tfg.repositorios.SemanaUserRepository;
import com.example.tfg.repositorios.UserRepository;

@Controller
public class ControladorSemanaUser {

	@Autowired
	private SemanaUserRepository repSemUser;
	@Autowired
	private SemanaRepository repSem;
	@Autowired
	private UserRepository repUser;
	
	
	//TODO:  no entiendo porque lo tengo que lanzar 2 veces para que lo enseñe
	@GetMapping("/semUser")
	@ResponseBody Object getSemUser(@RequestParam int idUser) {
		List<SemanaUser> semanas = new ArrayList<SemanaUser>();
		Iterable<SemanaUser> iterSemUser = repSemUser.findAll();
		for(SemanaUser semUser : iterSemUser) {
			if(semUser.getUser().getId() == idUser) {
				semanas.add(semUser);
			}
		}		
		if(semanas.size()< 1) {
			anadir(idUser);	
			for(SemanaUser semUser : iterSemUser) {
				if(semUser.getUser().getId() == idUser) {
					semanas.add(semUser);
				}
			}			
		}
		
		return semanas;
	}
	
	@PostMapping("/semUser")
	@ResponseBody Object postSemUser(@RequestBody SemanaUser semUser) {
		repSemUser.save(semUser);
		return semUser;
	}
	
	private void crear(Semana semana, User user, int seleccionado) {
		SemanaUser semUser = new SemanaUser();
		semUser.setSemana(semana);
		semUser.setUser(user);
		semUser.setSeleccionado(seleccionado);
		postSemUser(semUser);
	}
	
	private void anadir(int idUser) {
		Iterable<Semana> iterSem = repSem.findAll();
		Iterable<User> iterUser = repUser.findAll();
		for(User user : iterUser) {
			if(user.getId() == idUser) {
				for(Semana semana : iterSem) {					
					if(semana.getNombre().equals("Facil")) {
						crear(semana,user,1);
					}
					
					if(semana.getNombre().equals("Medium") ||
							semana.getNombre().equals("Dificil")) {
						crear(semana,user,0);
					}
				}
			}
		}		
	}
}
