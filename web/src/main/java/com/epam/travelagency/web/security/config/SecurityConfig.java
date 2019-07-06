package com.epam.travelagency.web.security.config;

import com.epam.travelagency.web.security.handler.SuccessHandler;
import com.epam.travelagency.web.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@EnableWebSecurity
@Configuration
@ComponentScan("com.epam.travelagency.web.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/tours", "/tour", "/hotels",
                        "/bootstrap/**", "/css/**", "/js/**",
                        "/fonts/**", "/img/**").permitAll()
                .antMatchers("/profile/*", "/user/*").authenticated()
                .antMatchers("/login", "/signup").anonymous()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .loginProcessingUrl("/process_login")
                .successHandler(successHandler())
                .failureUrl("/login?error=")
                .and()
                .logout()
                .logoutUrl("/process_logout")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/error/403");
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new SuccessHandler();
    }


}
