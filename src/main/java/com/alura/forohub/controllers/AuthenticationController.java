package com.alura.forohub.controllers;


import com.alura.forohub.domain.usuario.DatosAutenticacion;
import com.alura.forohub.domain.usuario.Usuario;
import com.alura.forohub.infra.security.DatosTokenJWT;
import com.alura.forohub.infra.security.TokenService; // Verificar que se importe localmente y NO de otro lado (e. g. Springboot)
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutenticacion datosAutenticacion){

        var authenticationToken = new UsernamePasswordAuthenticationToken(
                datosAutenticacion.username(),
                datosAutenticacion.password()
        );

        var autenticacion = authenticationManager.authenticate(authenticationToken);

        // Token enviado al usuario
        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
