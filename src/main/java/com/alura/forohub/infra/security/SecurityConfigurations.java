package com.alura.forohub.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Habilitar la seguridad a nivel de método
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    // @Bean - Carga securityFilterChain para que Security lo pueda leer
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Los filtros de Spring se ejecutan primero. Por ello,
        return http
                .csrf(csrf -> csrf.disable())   // Ataque usando cookies
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> {
                    req.requestMatchers(HttpMethod.POST, "login").permitAll();  // Permite cualquier Request al login
                    // req.requestMatchers(HttpMethod.POST, "usuarios").permitAll(); // Asegurarse que el registro de usuarios esté permitido para darse de alta (VERIFICAR regla de negocio)
                    req.requestMatchers("/topicos/**").authenticated(); // Añade esta línea para ser más específico
                    req.requestMatchers("/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll(); // Liberación para usar SpringDoc
                    req.anyRequest().authenticated();   // Bloquea cualquier otro Resquest que no sea login
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class )
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {

        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Este método es el Encoder de Bcrypt para poder encriptar las contraseñas y
     * poder almacenarlas en la tabla usuarios.
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
