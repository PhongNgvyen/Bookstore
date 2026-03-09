package bookstore.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.security.autoconfigure.web.servlet.PathRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/css/**", "/error").permitAll()
                .requestMatchers(PathRequest.toH2Console()).permitAll()
                .requestMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers(PathRequest.toH2Console())
            )
            .headers(headers -> headers
                .frameOptions(frame -> frame.sameOrigin())
            )
            .formLogin(formlogin -> formlogin
                .loginPage("/login")
                .defaultSuccessUrl("/booklist", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .permitAll()
            );

        return http.build();
    }
}