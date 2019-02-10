package com.karlchu.book.config;

import com.karlchu.book.core.service.impl.UserDetailsServiceImpl;
import com.karlchu.book.utility.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/login**",
                        "/favicon**",
                        "/mobile/**",
                        "/api/**",
                        "/ajax/**",
                        "/themes/**",
                        "/public/**",
                        "/books/**",
                        "/book**",
                        "/chapter**").permitAll()
                .antMatchers("/admin/**").hasRole(Constants.LOGON_DEFAULT_PERMISSION)
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
//                .loginProcessingUrl("/login")// this is default value
//                .defaultSuccessUrl("/") // this is default value
//                .failureUrl("/login?error") // this is default value
                .permitAll()
                .and()
                .exceptionHandling() // This is automatically applied when using WebSecurityConfigurerAdapter
                .accessDeniedPage("/403"); // @TODO: Not return 403 yet
    }

}