package com.excilys.cdb.configSpring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserDetailsService userService;

	public SpringSecurityConfig(UserDetailsService userService) {
		super();
		this.userService = userService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/css/**", "/js/**", "/flags/**").permitAll()

//				.antMatchers(HttpMethod.GET, "/", "/dashboard", "/error").permitAll()
//				.antMatchers(HttpMethod.GET, "/addComputer").hasRole("USER")
//				.antMatchers(HttpMethod.POST, "/addComputer").hasRole("USER")
//				.antMatchers(HttpMethod.GET, "/editComputer").hasRole("USER")
//				.antMatchers(HttpMethod.POST, "/editComputer").hasRole("USER")
//				.antMatchers(HttpMethod.POST, "/delete").hasRole("USER")
				.anyRequest().authenticated().
				and().formLogin().permitAll().and().logout().permitAll();
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
