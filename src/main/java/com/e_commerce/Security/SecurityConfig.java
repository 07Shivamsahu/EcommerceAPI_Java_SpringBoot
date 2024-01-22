//package com.e_commerce.Security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
////@DependsOn("userService")
//public class SecurityConfig {
////    @Autowired
////    private final UserDetailsServiceImpl userDetailsService;
////
////    public SecurityConfig(UserDetailsServiceImpl userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .userDetailsService(userDetailsService)
////                .passwordEncoder(passwordEncoder());
////    }
//
////    private final CustomUserDetailsService customUserDetailsService;
////
////    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
////        this.customUserDetailsService = customUserDetailsService;
////    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests()
////                .antMatchers("/users/login").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .logout()
////                .permitAll();
////    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
////    }
//}
