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

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PromotionRepository promotionRepository;

    @GetMapping("/")
    public String redirectRoot() {
        return "forward:/productos";
    }

    @GetMapping("/productos")
    public String shop(Model model, HttpServletRequest request) {
        List<Product> products = productRepository.findAll();
        products.sort(Comparator.comparing(Product::getName));

        model.addAttribute("products", products);
        model.addAttribute("admin", request.isUserInRole("ADMIN"));
        model.addAttribute("logged", request.getUserPrincipal() == null);

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
    public String createProduct(Model model, @RequestParam String name, @RequestParam float price, @RequestParam int promotion,
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

    @GetMapping("/upload_product")
    public String createProductView() {
        return "upload_product";
    }

}