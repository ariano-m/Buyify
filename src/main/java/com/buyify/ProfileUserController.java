package com.buyify;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileUserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/profile")
	public String userProfileView(Model model) {
		
		List<User> users = userRepository.findAll();
		User user = users.get(0);//prueba
		List<Order> orders = user.getOrders();
		model.addAttribute("username", user.getName());
		model.addAttribute("email", user.getEmail());
		model.addAttribute("pedido", orders);
		model.addAttribute("user", user);

		
		
		return "user_profile";
	}
}
