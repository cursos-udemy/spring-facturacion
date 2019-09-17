package com.jendrix.udemy.facturacion.app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.jendrix.udemy.facturacion.app.handler.LoginSuccessHandler;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginSuccessHandler loginSuccessHandler;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// Configuramos los usuarios de la aplicacion
		// this.configureInMemory(auth);
		//this.configureJDBCAuthentication(auth);
		this.configureUserDetailsService(auth);
	}

	protected void configureInMemory(AuthenticationManagerBuilder auth) throws Exception {
		UserBuilder userBuilder = User.builder().passwordEncoder(this.passwordEncoder::encode);
		auth.inMemoryAuthentication()
				.withUser(userBuilder.username("admin").password("12345").roles("ADMIN", "USER"))
				.withUser(userBuilder.username("willy").password("willy").roles("USER"));
	}

	protected void configureJDBCAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		StringBuffer sqlRoles = new StringBuffer();
		sqlRoles.append("select u.username, a.role ");
		sqlRoles.append("from authority a inner join user u on a.user_id = u.id ");
		sqlRoles.append("where u.username = ? ");

		auth.jdbcAuthentication()
				.dataSource(this.dataSource)
				.passwordEncoder(this.passwordEncoder)
				.usersByUsernameQuery("select username, password, enabled from user where username = ?")
				.authoritiesByUsernameQuery(sqlRoles.toString());
	}

	protected void configureUserDetailsService(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(this.userDetailsService)
				.passwordEncoder(this.passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Autorizacion a rutas y recursos
		// Aplico restricciones de rutas de factura con annotation en los controllers
		http.authorizeRequests()
				.antMatchers("/", "/css/**", "/js/**", "/images/**", "/fonts/**", "/vendor/**", "/cliente/listar", "/errors/**").permitAll()
				.antMatchers("/uploads/**").hasAnyRole("USER")
				.antMatchers("/cliente/view/**").hasAnyRole("USER")
				.antMatchers("/cliente/form/**").hasAnyRole("ADMIN")
				.antMatchers("/cliente/eliminar/**").hasAnyRole("ADMIN")
				// .antMatchers("/factura/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and().formLogin().successHandler(this.loginSuccessHandler).loginPage("/login").permitAll()
				.and().logout().permitAll()
				.and().exceptionHandling().accessDeniedPage("/403");
	}

}
