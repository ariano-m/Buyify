package com.buyify;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@Controller
public class ShopController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/productos")
    public String shop(Model model) {
        List<Product> products = productRepository.findAll();

        Map<Product, Promotion> map = new HashMap<>();
        for (Product product : products) {
            map.put(product, promotionRepository.findByProductId(product.getId()));
        }

        Set<Map.Entry<Product, Promotion>> entrySet = map.entrySet();

        model.addAttribute("entrySet", entrySet);

        return "shop";
    }

    @GetMapping("/productos/{id}")
    public String viewProduct(Model model, @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);
        Optional<List<Review>> reviewList = product.map(Product::getReviews);

        Promotion promotion = promotionRepository.findByProductId(id);

        model.addAttribute("product", product.get());
        model.addAttribute("id", id);
        model.addAttribute("review", reviewList.get());
        model.addAttribute("promotion", promotion);

        return "product_details";
    }

}
