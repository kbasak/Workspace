package com.appoinment.clinic;

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


@EnableWebSecurity
@Configuration
class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
					.authorizeRequests()
					.antMatchers("/patient/register").permitAll()
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

	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
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
