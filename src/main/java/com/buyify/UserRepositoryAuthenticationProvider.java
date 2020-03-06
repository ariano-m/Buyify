package com.buyify;

import java.util.ArrayList;
import java.util.List;

import com.buyify.user.User;
import com.buyify.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {

        User user = userRepository.findByUsername(auth.getName());

        if (user == null) {
            throw new BadCredentialsException("Something was wrong");
        }

        String password = (String) auth.getCredentials();
        System.out.println(user.getPassword());
        System.out.println(password);
        if (!new BCryptPasswordEncoder().matches(password, user.getPassword())) {
            throw new BadCredentialsException("Something was wrong");
        }
//        if (password != user.getPassword()) {
//            throw new BadCredentialsException("Something was wrong");
//        }


        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : user.getRoles()) {
            roles.add(new SimpleGrantedAuthority(role));
        }

        return new UsernamePasswordAuthenticationToken(user.getUsername(), password, roles);
    }

    @Override
    public boolean supports(Class<?> authenticationObject) {
        return true;
    }
}