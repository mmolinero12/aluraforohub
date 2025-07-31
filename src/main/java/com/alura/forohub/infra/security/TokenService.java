package com.alura.forohub.infra.security;

import com.alura.forohub.domain.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    /**
     * Método para generar un Token
     * @param usuario
     * @return
     */
    public String generarToken(Usuario usuario){

        try {
            var algorithm = Algorithm.HMAC256("secret");
            return JWT.create()
                    .withIssuer("API alura.forohub")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(fechaExpiracion())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            // Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Error al generar el token JWT", exception);
        }
    }

    private Instant fechaExpiracion(){
        return LocalDateTime.now().plusHours(8).toInstant(ZoneOffset.of("-06:00"));
    }


    public String getSubject(String tokenJWT){
        try {
            var algorithm = Algorithm.HMAC256("secret");
            return JWT.require(algorithm)
                    // specify any specific claim validations
                    .withIssuer("API alura.forohub")
                    // reusable verifier instance
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw  new RuntimeException("Token JWT inválido o expirado");
        }

    }
}


