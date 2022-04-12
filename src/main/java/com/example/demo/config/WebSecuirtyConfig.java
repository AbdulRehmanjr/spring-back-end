package com.example.demo.config;

import com.example.demo.service.UserDetailsServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecuirtyConfig  extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private UserDetailsServiceImp userDetailsService;
    
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
 
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    //     auth.inMemoryAuthentication().withUser("user").password("password").roles("USER");
    // }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // allow everone to access every url and aloow to signup
        http
        .cors()
        .and()
        .authorizeRequests().antMatchers("/api/products","/api/signup","/api/product-category","/api/login","/api/login/**","/api/products/**","/api/product-category/**","/api/search","api/","/").permitAll()
        .antMatchers("/api/memberpage").hasAuthority("MEMBER")
        .antMatchers("/api/admin/adminpage","api/admin/**","/api/memberpage").hasAuthority("ADMIN").and()
                .formLogin()
                .loginPage("/api/login")
                .loginProcessingUrl("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/api/memberlogin", true)
        .and()
        .csrf().disable();
        super.configure(http);
    }

}
