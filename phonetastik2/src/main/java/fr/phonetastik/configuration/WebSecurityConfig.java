package fr.phonetastik.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin().loginPage("/login") // the custom login page
				.defaultSuccessUrl("/home") // the landing page after a successful login
				.and().logout().logoutSuccessUrl("/login").and().authorizeRequests()
				.antMatchers("/webjars/**", "/", "/images/**", "/login", "/home", "/acceuil", "/css/**",
						"/clientele/**")

				.permitAll().anyRequest().authenticated();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();

	}

}
