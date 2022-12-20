package com.example.WebMeetingPlanner;

//import com.example.WebMeetingPlanner.Service.CustomLoginFailureHandler;
import com.example.WebMeetingPlanner.Service.CustomLoginFailureHandler;
import com.example.WebMeetingPlanner.Service.CustomUserDetailsService;
import com.example.WebMeetingPlanner.controller.LoginController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService()
    {
        return new CustomUserDetailsService();
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.authenticationProvider(authenticationProvider());
    }




    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/css/**","/js/**","/bootstrap/**", "/images/**","/bootstrap-datetimepicker.js/**","/tempusdominus-bootstrap-4/**","/jquery/**","/moment/**",
                        "/index", "/", "/register", "/meeting_form","homepage", "/submit-registration","/process_register","/meeting_details","/forgot_password**","/reset_password").permitAll()
                .antMatchers("/"). hasRole("Organisation Officer")
                .antMatchers("/user_form","homepage","/AdminPage/**").hasRole("Admin")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")

//                .successHandler(loginSuccessHandler)
                .permitAll()
                .loginProcessingUrl("/perform-login")

                .usernameParameter("email")
                .passwordParameter("password")

                .defaultSuccessUrl("/homepage",  true)
//                .failureUrl("/login-error")
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/index")
                .permitAll();
    }

    @Autowired
    private CustomLoginFailureHandler loginFailureHandler;

//    @Autowired
//    private CustomLoginSuccessHandler loginSuccessHandler;


//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeRequests()
//                .antMatchers("/list_users")
//                .authenticated()
//                .anyRequest().permitAll()
//                .and()
//                .formLogin()
//                .usernameParameter("email")
//                .defaultSuccessUrl("/list_users")
//                .permitAll()
//                .and()
//                .logout().logoutSuccessUrl("/").permitAll();
//    }
}
