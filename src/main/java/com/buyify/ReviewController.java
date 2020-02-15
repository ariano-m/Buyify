package com.buyify;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@PostMapping("/productos/{id}/nuevaReview")
	public String linkTowriteReview(Model model, @PathVariable long id, @RequestParam String reviewText, @RequestParam Long productId) {
		List<User> users = userRepository.findAll();
		User user = users.get(0);//Prueba
		LocalDate localDate = java.time.LocalDate.now();
		Date date = java.sql.Date.valueOf(localDate);
		
		Optional<Product> producto = productRepository.findById(productId);
		
		Review review = new Review(producto.get(), user, date, reviewText);
		reviewRepository.save(review);
		
		model.addAttribute("id", id);
		return "reviewAdded";
	}
	
}
