package pe.edu.upc.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.spring.auth.handler.LoginSuccessHandler;
import pe.edu.upc.spring.serviceimpl.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private LoginSuccessHandler successHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		try {
			http
			.authorizeRequests()
				.antMatchers("/actor/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/critico/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/textoCritica/registrar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/textoCritica/modificar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/textoCritica/eliminar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/textoCritica/listar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/textoCritica/buscar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/director/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/genero/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/peliculaActor/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/pelicula/registrar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/pelicula/modificar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/pelicula/eliminar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/pelicula/listar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/pelicula/buscar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/noticia/registrar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/noticia/modificar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/noticia/eliminar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/noticia/listar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/noticia/buscar").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/peliculaGenero/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/reaccion/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/textoCritica/reviews").access("hasRole('ROLE_USER')")//ROLE_USER
				.antMatchers("/pelicula/verPeliculas").access("hasRole('ROLE_USER')")
				.antMatchers("/pelicula/verPelicula").access("hasRole('ROLE_USER')")
				.antMatchers("/noticia/verNoticias").access("hasRole('ROLE_USER')")
				.antMatchers("/noticia/verNoticia").access("hasRole('ROLE_USER')")
				.antMatchers("/noticia/eliminarComentario").access("hasRole('ROLE_USER')")
				.antMatchers("/noticia/registraComentario").access("hasRole('ROLE_USER')")
				.antMatchers("/noticia/interaccionLike").access("hasRole('ROLE_USER')")
				.antMatchers("/noticia/interaccionDislike").access("hasRole('ROLE_USER')")
				.antMatchers("/usuarioReview/**").access("hasRole('ROLE_USER')")
				.antMatchers("/welcome/bienvenido").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
				.antMatchers("/welcome/bienvenidoN").permitAll()
				.antMatchers("/auth/registro").permitAll()
				.antMatchers("/","/imagenes/**","/img/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.successHandler(successHandler)
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/welcome/bienvenido", true)
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/welcome/bienvenidoN")
				.permitAll()
				.and()
			.exceptionHandling()
				.accessDeniedPage("/error_403");
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}
}
