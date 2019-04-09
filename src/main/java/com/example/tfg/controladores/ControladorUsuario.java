package com.example.tfg.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.Usuario;
import com.example.tfg.repositorios.UserRepository;

@Controller
public class ControladorUsuario {

	@Autowired
	private UserRepository repUser;
	
	@PostMapping("/user")
	@ResponseBody Object postUser(@RequestBody Usuario user) {
		if(user.getNombre() == null)
			return null;
		if(user.getPassword() == null)
			return null;
		
		repUser.save(user);
		return user;
	}
	
	@GetMapping("/user")
	@ResponseBody Object getUser(@RequestParam String nombre) {
		Iterable<Usuario> iterUser = repUser.findAll();
		for (Usuario user : iterUser) {
			if(user.getNombre().equals(nombre))
				return user;
		}
		return null;
	}
	
	@DeleteMapping("/user")
	@ResponseBody Object delUser(@RequestParam Integer userId) {
		Iterable<Usuario> iterUser = repUser.findAll();
		for(Usuario user : iterUser) {
			if(user.getId() == userId) {
				repUser.delete(user);
				return user;
			}
		}		
		return null;
	}
	
	@PutMapping("/user")
	@ResponseBody Object putUser(@RequestBody Usuario newUser) {
		Iterable<Usuario> iterUser = repUser.findAll();
		for(Usuario user : iterUser) {
			if(user.getId() == newUser.getId()) {
				if(newUser.getNombre() == null)
					return null;
				if(newUser.getPassword() == null)
					return null;
				
				if(!user.getNombre().equals(newUser.getNombre()))
					user.setNombre(newUser.getNombre());
				if(!user.getPassword().equals(newUser.getPassword()))
					user.setPassword(newUser.getPassword());
				
				repUser.save(user);
				return user;
			}
		}
		return null;
	}
}
