package com.buyify.product;

import com.buyify.promotion.Promotion;
import com.buyify.promotion.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping("/productos")
    public String shop(Model model) {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getName));

        model.addAttribute("products", products);

        return "shop";
    }

    @GetMapping("/productos/{id}")
    public String viewProduct(Model model, @PathVariable long id) {
        Optional<Product> product = productRepository.findById(id);

        model.addAttribute("product", product.get());

        return "product_details";
    }

    @PostMapping("/busqueda")
    public String findByName(Model model, @RequestParam String name) {
        List<Product> product_list = productRepository.findByNameIsLike(name);
        model.addAttribute("products", product_list);
        return "shop";
    }

    @PostMapping("/productos/nuevoProducto")
    public String createProduct(Model model, @RequestParam String name, @RequestParam int price, @RequestParam int promotion,
                                @RequestParam int stock, @RequestParam String category,
                                @RequestParam String url, @RequestParam String description) {

        Product newProduct = new Product(name, category, price, stock, description, url);
        productRepository.save(newProduct);

        if (promotion > 0) {
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
    
    @GetMapping("/upload_product.html")
    public String formNewProduct(Model model) {
    	List<Category> categories = new ArrayList<>();
    	for(Category category : Category.values()) {
    		categories.add(category);
    	}
    	model.addAttribute("categories",categories);
    	return "upload_product";
    }

}