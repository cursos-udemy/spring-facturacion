package com.jendrix.udemy.facturacion.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// @Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Configuramos los usuarios de la aplicacion
		PasswordEncoder encoder = getPasswordEncoder();
		UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
		auth.inMemoryAuthentication()
				.withUser(userBuilder.username("admin").password("12345").roles("ADMIN", "USER"))
				.withUser(userBuilder.username("willy").password("willy").roles("USER"));
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Autorizacion a rutas y recursos
		http.authorizeRequests()
				.antMatchers("/", "/css/**", "/js/**", "/images/**", "/cliente/listar").permitAll()
				.antMatchers("/uploads/**").hasAnyRole("USER")
				.antMatchers("/cliente/view/**").hasAnyRole("USER")
				.antMatchers("/cliente/form/**").hasAnyRole("ADMIN")
				.antMatchers("/cliente/eliminar/**").hasAnyRole("ADMIN")
				.antMatchers("/factura/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin().and().httpBasic();
	}

}
