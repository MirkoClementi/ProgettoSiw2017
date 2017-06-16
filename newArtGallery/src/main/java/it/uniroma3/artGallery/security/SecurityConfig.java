package it.uniroma3.artGallery.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@RestController
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;


	@RequestMapping("/user")
	public Principal user(Principal principal) {
		return principal;
	}
	
	@Bean
	public UserDetailsService userDetailsService()  {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
		manager.createUser(User.withUsername("admin").password("password").roles("USER","ADMIN").build());
		return manager;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/login")
				.permitAll().antMatchers("**").hasRole("USER")
			.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
			.permitAll();
		
	}

//	@Configuration
//	@Order(1)                                                        
//	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
//		protected void configure(HttpSecurity http) throws Exception {
//			http
//			.antMatcher("/admin/**")                               
//			.authorizeRequests()
//			.anyRequest().hasRole("ADMIN")
//			.and()
//			.httpBasic();
//
//		}
//	}

}