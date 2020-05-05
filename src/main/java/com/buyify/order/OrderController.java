package com.buyify.order;

import com.buyify.product.Product;
import com.buyify.product.ProductRepository;
import com.buyify.user.User;
import com.buyify.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/new_order")
    public String showProductList(Model model) {
        List<Product> product_list = productRepository.findAll();
        model.addAttribute("products", product_list);
        return "new_order";
    }

    @PostMapping("/realizado")
    public RedirectView order(@RequestParam(name = "product_list") String[] productNames, HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName()).get();
        LocalDate localDate = java.time.LocalDate.now();
        Date date = java.sql.Date.valueOf(localDate);

        List<Product> products = new ArrayList<>();
        for (String productName : productNames) {
            Product product = productRepository.findByName(productName);
            products.add(product);
        }

        Order order = new Order(user, date, products);
        orderRepository.save(order);
        
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:9999/pedido/" + order.getId(), HttpMethod.GET, null, Void.class);

        RedirectView redirectView = new RedirectView("profile");
        redirectView.setExposeModelAttributes(false);
        return redirectView;
    }

    @GetMapping("/factura/{id}")
    public ResponseEntity<?> invoice(@PathVariable long id, HttpServletRequest request) {
        Optional<Order> order = orderRepository.findById(id);

        if (!request.getUserPrincipal().getName().equalsIgnoreCase(order.get().getUser().getUsername())) {
            return new ResponseEntity<>("Forbidden", HttpStatus.FORBIDDEN);
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_OCTET_STREAM));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange("http://localhost:9999/factura/" + id, HttpMethod.GET, entity, byte[].class);
    }

}
