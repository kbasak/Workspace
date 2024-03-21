package com.service.coupon.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
public class WebSecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;

	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityContextRepository securityContextRepository() {
		return new HttpSessionSecurityContextRepository();
	}

	@Bean
	AuthenticationManager authManager() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return new ProviderManager(provider);
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.httpBasic();
		http.authorizeHttpRequests()
				.antMatchers(HttpMethod.GET, "/coupon/getCode/**", "/showGetCoupon", "/getCoupon")
				.hasAnyRole("USER", "ADMIN")
				.antMatchers(HttpMethod.GET, "/showCreateCoupon", "/createCoupon", "/createResponse").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/coupon/create", "/saveCoupon").hasRole("ADMIN")
				.antMatchers(HttpMethod.POST, "/getCoupon").hasAnyRole("ADMIN", "USER")
				.antMatchers("/", "/login","showReg","registerUser").permitAll()
				.and().logout().logoutSuccessUrl("/")
				.and().csrf().disable();

		http.securityContext(context -> context.requireExplicitSave(true));

		return http.build();
	}

}
