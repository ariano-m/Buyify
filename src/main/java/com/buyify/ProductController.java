package com.buyify;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {
	
	@Autowired
	private ProductRepository repository;
	@Autowired
	private Product product;
	
	@GetMapping("/newProduct")
    public String newProduct(Model model, 
    		@RequestParam(required=false) String name, 
    		@RequestParam(required=false) String category,
    		@RequestParam(required=false) String price,
    		@RequestParam(required=false) String stock, 
    		@RequestParam(required=false) String description,
    		@RequestParam(required=false) String URL) {
        model.addAttribute("name", name);
        model.addAttribute("category", category);
        model.addAttribute("price", price);
        model.addAttribute("stock", stock);
        model.addAttribute("description", description);
        model.addAttribute("URL", URL);
        
        product = new Product(name,category,Float.parseFloat(price),Integer.parseInt(stock),description,URL);
        repository.save(product);
        
        List<Product> query = repository.findAll();
        for (Product p : query) {
            System.out.println("PRODUCTO= " + p.getName());
        }

        return "product_added";
    }

}
