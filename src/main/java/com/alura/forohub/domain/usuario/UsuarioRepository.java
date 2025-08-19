package com.alura.forohub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByUsername(String username);


    @Query("""
            select count(u) > 0 from Usuario u
            where
            u.username = :username
            """
    )
    boolean existsByUsername(String username);
}
