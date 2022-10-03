//package com.BTL.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//	
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//
//	@Bean
//    public AuthenticationProvider authenticationProvider() {
//    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//    	provider.setUserDetailsService(userDetailsService);
//    	provider.setPasswordEncoder(new BCryptPasswordEncoder());
//    	
//		return provider;
//    }
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//    	
//        http
//        .authorizeRequests()
//        	.antMatchers("/**").permitAll()
////        .antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
////        	.antMatchers("/admin/**").permitAll()
////            .antMatchers("/", "/shoppingCart", "/shoppingCart/**", "/**").permitAll() // Cho phép tất cả mọi người truy cập vào 2 địa chỉ này
//            .anyRequest().authenticated() // Tất cả các request khác đều cần phải xác thực mới được truy cập
//            .and()
//        .formLogin() // Cho phép người dùng xác thực bằng form login
//            .defaultSuccessUrl("/")
//            .permitAll() // Tất cả đều được truy cập vào địa chỉ này
//            .loginPage("/loginTest")
//            .and()
//        .logout() // Cho phép logout
//            .permitAll();
//    	
//    }
//}
