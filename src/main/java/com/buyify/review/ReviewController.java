package com.buyify.review;

import com.buyify.product.Product;
import com.buyify.product.ProductRepository;
import com.buyify.user.User;
import com.buyify.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/productos/{id}/nuevaReview")
    public String linkTowriteReview(Model model, @PathVariable long id, @RequestParam String reviewText, HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName()).get();
        LocalDate localDate = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);

        Optional<Product> producto = productRepository.findById(id);

        Review review = new Review(producto.get(), user, date, reviewText);
        reviewRepository.save(review);

        model.addAttribute("id", id);
        return "reviewAdded";
    }

}