package com.buyify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Controller
public class ShopController {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/productos")
    public String shop(Model model) {
        List<Product> products = productRepository.findAll();
        model.addAttribute("product", products);

        return "shop";
    }

    @GetMapping("/productos/{id}")
    public String viewProduct(Model model, @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);

        model.addAttribute("product", product.get());

        return "product_details";
    }
}
