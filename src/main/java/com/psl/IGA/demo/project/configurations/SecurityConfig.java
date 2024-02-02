package com.psl.IGA.demo.project.configurations;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.psl.IGA.demo.project.service.CustomSuccessHandler;
import com.psl.IGA.demo.project.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	CustomSuccessHandler customSuccessHandler;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Value("${spring.mail.host}")
	private String mailHost;
	@Value("${spring.mail.port}")
	private String mailPort;
	@Value("${spring.mail.username}")
	private String mailUsername;
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl javaMailSender=new JavaMailSenderImpl();
		javaMailSender.setHost(mailHost);
		javaMailSender.setPort(Integer.parseInt(mailPort));
		javaMailSender.setUsername(mailUsername);
		javaMailSender.setPassword(mailPassword);
		
		Properties props=javaMailSender.getJavaMailProperties();
		props.put("mail.smtp.starttls.enable", props);
		return javaMailSender;
		
	}
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain  securityFilterChain(HttpSecurity http)  throws Exception {
		http
		.csrf(c -> c.disable())
		.authorizeHttpRequests(request -> request.requestMatchers("/admin-page")
				.hasAuthority("ADMIN").requestMatchers("/user-page","/profile").hasAuthority("USER")
				.requestMatchers("/registration", "/css/**","/forgotpass/**","/forgotpass/getverified/otp").permitAll()
				.anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
						.successHandler(customSuccessHandler).permitAll())
				
				.logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
						.logoutSuccessUrl("/login?logout").permitAll());
		
		return http.build();
	}
	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
	}
}
