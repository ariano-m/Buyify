package com.buyify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/profile")
	public String userProfileView(Model model) {
		
		List<User> users = userRepository.findAll();
		User user = users.get(0);//prueba
		model.addAttribute("user", user);	
		return "user_profile";
	}
	
	@PostMapping("/nuevoUsuario")
	public String registrarse(Model model, @RequestParam String name, @RequestParam String username, 
			@RequestParam String password, @RequestParam String email) {
		
		User user = new User(name, username, email, password);
		userRepository.save(user);
		model.addAttribute("username", user.getUsername());
		
		return "registered";
	}
}
