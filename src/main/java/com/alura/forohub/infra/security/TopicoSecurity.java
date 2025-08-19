package com.alura.forohub.infra.security;

import com.alura.forohub.domain.topico.TopicoRepository;
import com.alura.forohub.domain.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class TopicoSecurity {
    @Autowired
    private TopicoRepository topicoRepository;

    /**
     * Este método valida si un usuario es el propietario/creador
     * @param id
     * @return
     */
    public boolean isOwner(Long id) {

        System.out.println("isOwner OK");

        // Obtenemos el objeto de autenticación del contexto de seguridad
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Si no hay autenticación, no se puede verificar la propiedad
        if (authentication == null || !authentication.isAuthenticated()) {
            return false;
        }

        // El principal del objeto de autenticación es el objeto Usuario que implementa UserDetails
        Object principal = authentication.getPrincipal();

        // Verificamos que el principal sea una instancia de nuestro objeto Usuario
        if (!(principal instanceof Usuario)) {
            return false; // No podemos verificar la propiedad si el principal no es un Usuario
        }

        // Obtenemos el ID del usuario autenticado
        Long authenticatedUserId = ((Usuario) principal).getId();

        // Imprimimos el ID del usuario autenticado para depuración
        System.out.println("ID del usuario autenticado: " + authenticatedUserId);

        // Buscamos el tópico por su ID
        return topicoRepository.findById(id).map(topico -> {
            // Imprimimos el ID del creador del tópico para depuración
            System.out.println("ID del creador del tópico: " + topico.getUsuario().getId());

            // Verificamos si el ID del creador del tópico coincide con el del usuario autenticado
            return Objects.equals(topico.getUsuario().getId(), authenticatedUserId);
        }).orElse(false);
    }
}
