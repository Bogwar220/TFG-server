package com.example.tfg.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.tfg.modelos.User;
import com.example.tfg.repositorios.UserRepository;

@Controller
public class ControladorUsuario {

	@Autowired
	private UserRepository repUser;
	
	@PostMapping("/user")
	@ResponseBody Object postUser(@RequestBody User user) {
		if(user.getUsername() == null)
			return null;
		if(user.getPassword() == null)
			return null;
		
		repUser.save(user);
		return user;
	}
	
	@GetMapping("/user")
	@ResponseBody Object getUser(@RequestParam String username, @RequestParam String password) {
		Iterable<User> iterUser = repUser.findAll();
		for (User user : iterUser) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password))
				return user;
		}
		return null;
	}
	
	@DeleteMapping("/user")
	@ResponseBody Object delUser(@RequestParam Integer userId) {
		Iterable<User> iterUser = repUser.findAll();
		for(User user : iterUser) {
			if(user.getId() == userId) {
				repUser.delete(user);
				return user;
			}
		}		
		return null;
	}
	
	@PutMapping("/user")
	@ResponseBody Object putUser(@RequestBody User user) {
		
		if(user.getId() == 0 && user.getUsername().equals("")) {
			return null;
		}
		
		Optional<User> opUser = repUser.findById((int) user.getId());
		
		User newUser = opUser.get();
		
		if(user.getPassword() != null) {
			newUser.setPassword(user.getPassword());
		}		
		if(user.getPeso() != 0) {
			newUser.setPeso(user.getPeso());
		}		
		if(user.getAltura() != 0) {
			newUser.setAltura(user.getAltura());
		}
		
		repUser.save(newUser);
		return user;
	}
}
