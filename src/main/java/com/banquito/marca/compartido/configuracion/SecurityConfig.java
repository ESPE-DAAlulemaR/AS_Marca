package com.banquito.marca.compartido.configuracion;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Value("${jwt.secret.user}")
    private String jwtUser;

    @Value("${jwt.secret.pass}")
    private String jwtPass;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Habilitar CORS
            .cors(Customizer.withDefaults())
            // Deshabilitar CSRF para APIs REST
            .csrf(csrf -> csrf.disable())
            // Configurar la autorización de solicitudes
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/publico/**").permitAll() // Rutas públicas
                .anyRequest().authenticated() // Todas las demás rutas requieren autenticación
            )
            // Configurar la autenticación básica
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService users() {
        // Crear usuarios en memoria (para propósitos de prueba)
        UserDetails usuario = User.withDefaultPasswordEncoder()
            .username(jwtUser)
            .password(jwtPass)
            .roles("USER")
            .build();
/*
        UserDetails admin = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("ADMIN")
            .build();
*/
        return new InMemoryUserDetailsManager(usuario);
    }
}
