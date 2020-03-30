package com.buyify.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Controller
public class UserController implements Serializable {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String userProfileView(Model model, HttpServletRequest request) {
        User user = userRepository.findByUsername(request.getUserPrincipal().getName()).get();
        model.addAttribute("user", user);
        return "user_profile";
    }

    @GetMapping("/signup")
    public String registerView() {
        return "signup";
    }

    @PostMapping("/nuevoUsuario")
    public String registrarse(Model model, @RequestParam String name, @RequestParam String username,
                              @RequestParam String password, @RequestParam String email) {
        User user = userRepository.save(new User(name, username, email, password, "USER"));

        model.addAttribute("username", user.getUsername());

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange("http://localhost:9999/mail/" + user.getId(), HttpMethod.GET, null, Void.class);

        return "registered";
    }
}
