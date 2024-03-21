package com.hcm.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hcm.login.filters.JwtRequestFilter;
import com.hcm.login.service.UserServiceImpl;

@EnableWebSecurity
@Configuration
class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
					.authorizeRequests()
					.antMatchers("/login","/validate","/health-check","/register","/addmember","/member/uname/{userName}","/users",
							"/physicianid/{physicianid}","/physicianname/{physicianname}",
							"/name/{fname}-{lname}","/memberid/{memberid}",
							"/searchMem").permitAll()
					.antMatchers("/h2-console/**").permitAll()
					.antMatchers("/v2/api-docs",
			                "/configuration/ui",
			                "/swagger-resources/**",
			                "/configuration/security",
			                "/swagger-ui.html",
			                "/webjars/**").permitAll()
					.anyRequest().authenticated()
					.and().sessionManagement()
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
