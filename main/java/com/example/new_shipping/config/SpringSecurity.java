package com.example.new_shipping.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/").hasRole("ADMIN")
                                .requestMatchers("/index").hasRole("ADMIN")
                                .requestMatchers("/new").hasRole("ADMIN")
                                .requestMatchers("/edit/**").hasRole("ADMIN")
                                .requestMatchers("/save").hasRole("ADMIN")
                                .requestMatchers("/delete/**").hasRole("ADMIN")
                                .requestMatchers("/users").hasRole("ADMIN")
                                .requestMatchers("/blog").hasRole("ADMIN")
                                .requestMatchers("/new_blog_entry").hasRole("ADMIN")
                                .requestMatchers("/edit_blog_entry/**").hasRole("ADMIN")
                                .requestMatchers("/delete_blog_entry/**").hasRole("ADMIN")
                                .requestMatchers("/save_blog_entry").hasRole("ADMIN")
                                .requestMatchers("/blog_entry/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/cargo/**").hasRole("ADMIN")
                                .requestMatchers("/api/v1/blog/**").hasRole("ADMIN")
                                .requestMatchers("/check").hasRole("ADMIN")
                                .requestMatchers("/favicon.ico").permitAll()



                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/index", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}