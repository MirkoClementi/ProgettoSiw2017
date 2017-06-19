package it.uniroma3.artGallery.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@EnableOAuth2Sso
public class Oauth2Config extends WebSecurityConfigurerAdapter{
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
			authorizeRequests()
				.antMatchers("/")
				.permitAll().antMatchers("/painting","/artist").hasRole("USER")
			.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
			.permitAll();
		
	}
	
	
	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.antMatcher("/login").authorizeRequests().antMatchers( "/login**", "/webjars/**").permitAll().anyRequest()
//				.authenticated();
//	}

}
