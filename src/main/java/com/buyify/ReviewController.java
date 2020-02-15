package com.buyify;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReviewController {
	
	@PostMapping("/productos/{id}/nuevaReview")
	public String linkTowriteReview(Model model, @PathVariable long id, @RequestParam String review) {
		model.addAttribute("id", id);
		return "reviewAdded";
	}
	
}
