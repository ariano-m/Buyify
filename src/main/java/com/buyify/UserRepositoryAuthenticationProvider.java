package com.buyify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<User> user = userRepository.findByUsername(auth.getName());

        if (!user.isPresent()) {
            throw new BadCredentialsException("Something was wrong");
        }

        String password = (String) auth.getCredentials();
        System.out.println(user.get().getPassword());
        System.out.println(password);
        if (!new BCryptPasswordEncoder().matches(password, user.get().getPassword())) {
            throw new BadCredentialsException("Something was wrong");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        for (String role : user.get().getRoles()) {
            roles.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new UsernamePasswordAuthenticationToken(user.get().getUsername(), password, roles);
    }

    @Override
    public boolean supports(Class<?> authenticationObject) {
        return true;
    }
}