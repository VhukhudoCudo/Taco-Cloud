package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain ( HttpSecurity http ) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/design", "/orders").hasRole("USER")
                    .antMatchers("/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                .logout()
                    .logoutSuccessUrl("/")
                .and()
                .csrf()
                    .ignoringAntMatchers("/h2-console/**")
                .and()
                .headers()
                    .frameOptions().sameOrigin();

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder () {
        return new BCryptPasswordEncoder();
    }
}

