package com.buyify.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

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

        User user = new User(name, username, email, password);
        userRepository.save(user);
        model.addAttribute("username", user.getUsername());

        return "registered";
    }
}
