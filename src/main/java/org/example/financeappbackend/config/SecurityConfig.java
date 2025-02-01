package org.example.financeappbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// For UserDetailsService (Optional: Implement your own)
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define the security filter chain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for simplicity; enable in production as needed
                .csrf(AbstractHttpConfigurer::disable)

                // Authorize HTTP requests
                .authorizeHttpRequests(auth -> auth
                        // Permit all users to access the home and static resources
                        .requestMatchers("/", "/public/**").permitAll()
                        // Restrict access based on roles
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        // All other requests need to be authenticated
                        .anyRequest().authenticated()
                )

                // Configure form login
                .formLogin(form -> form
                        .loginPage("/login") // Custom login page URL
                        .permitAll()
                )

                // Configure logout
                .logout(LogoutConfigurer::permitAll
                )

                // Optionally, configure HTTP Basic authentication
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    // Define a PasswordEncoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define an in-memory user store (For testing purposes)
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Define a regular user
        manager.createUser(User.withUsername("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build());
        // Define an admin user
        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("ADMIN")
                .build());
        return manager;
    }
}
