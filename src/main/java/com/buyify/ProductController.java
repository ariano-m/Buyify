package com.buyify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/productos/nuevoProducto")
    public String createProduct(Model model, @RequestParam String name, @RequestParam int price, @RequestParam int promotion,
                                @RequestParam int stock, @RequestParam String category,
                                @RequestParam String url, @RequestParam String description) {

        Product newProduct = new Product(name, category, price, stock, description, url);
        productRepository.save(newProduct);
        if(promotion > 0) {
        	Promotion newPromotion = new Promotion(promotion);
        	newPromotion.setProduct(newProduct);
        	promotionRepository.save(newPromotion);
        }

        
        model.addAttribute("id", newProduct.getId());

        return "product_added";
    }

    @GetMapping("/eliminar/{id}")
    public String deleteProduct(Model model, @PathVariable long id) {
    	productRepository.deleteById(id);
    	model.addAttribute("id", id);
    	return "product_deleted";
    }

}
