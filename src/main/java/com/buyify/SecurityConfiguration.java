package com.buyify;

import com.buyify.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
        http.authorizeRequests().antMatchers("/productos").permitAll();
        http.authorizeRequests().antMatchers("/productos/{id}").permitAll();
        http.authorizeRequests().antMatchers("/busqueda").permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().antMatchers("/loginerror").permitAll();
        http.authorizeRequests().antMatchers("/logout").permitAll();
        http.authorizeRequests().antMatchers("/signup").permitAll();
        http.authorizeRequests().antMatchers("/nuevoUsuario").permitAll();
        http.authorizeRequests().antMatchers("/upload_product").permitAll();


        // Private pages (all other pages)
        http.authorizeRequests().antMatchers("/profile").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/new_order").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/upload_product").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/eliminar/*").hasRole("ADMIN");
        http.authorizeRequests().antMatchers("/productos/{id}/nuevaReview").hasAnyRole("USER", "ADMIN");
        http.authorizeRequests().antMatchers("/realizado").hasAnyRole("USER", "ADMIN");

//        http.authorizeRequests().anyRequest().authenticated();

        // Login form
        http.formLogin().defaultSuccessUrl("/productos");


        // Logout
        http.logout().logoutUrl("/logout");
        http.logout().logoutSuccessUrl("/productos");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider((AuthenticationProvider) authenticationProvider);
    }

}


