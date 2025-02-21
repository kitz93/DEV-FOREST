package com.dev.forest.configuration.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.dev.forest.auth.util.JwtFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityConfiguration {

	private final JwtFilter filter;

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
		corsConfiguration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		return httpSecurity.formLogin(AbstractHttpConfigurer::disable).httpBasic(AbstractHttpConfigurer::disable)
				.csrf(AbstractHttpConfigurer::disable).cors(Customizer.withDefaults())
				.authorizeHttpRequests(requests -> {

					requests.requestMatchers(HttpMethod.GET, "/quizs/random").authenticated();
					requests.requestMatchers("/wrongs", "/wrongs/**").permitAll();
					requests.requestMatchers(HttpMethod.POST, "/members", "/members/login").permitAll();
					requests.requestMatchers(HttpMethod.GET, "/rankings").permitAll();
					requests.requestMatchers(HttpMethod.POST, "/rankings/insert").authenticated();
					requests.requestMatchers("/theorys", "/theorys/**").permitAll();
					requests.requestMatchers("/progress", "/progress/**").permitAll();
					requests.requestMatchers(HttpMethod.POST, "/members", "/members/login", "/members/sns",
							"/members/snsLogin").permitAll();
					requests.requestMatchers("/uploads/**").permitAll();
					requests.requestMatchers(HttpMethod.POST, "/members", "/members/login", "/members/sns", "/members/snsLogin").permitAll();
					requests.requestMatchers(HttpMethod.GET, "/members/myPage").authenticated();
					requests.requestMatchers(HttpMethod.PUT, "/members").authenticated();
					requests.requestMatchers(HttpMethod.DELETE, "/members","/members/sns", "/boards/**", "/reservations/**", "/studyings/**").authenticated();
					requests.requestMatchers("/admin/**").hasRole("ADMIN");
					requests.requestMatchers(HttpMethod.POST, "/boards/**", "/reservations/**", "/replys","/studyings").authenticated();
					requests.requestMatchers(HttpMethod.GET, "/boards/**", "/reservations/**", "/replys/**", "/studyings/**").permitAll();
					requests.requestMatchers(HttpMethod.PUT, "/boards/**", "/reservations/**").authenticated();
				})
				.sessionManagement(
						sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
