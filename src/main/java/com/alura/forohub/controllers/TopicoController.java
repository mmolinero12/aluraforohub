package com.alura.forohub.controllers;

import com.alura.forohub.domain.topico.DatosRegistroTopico;
import com.alura.forohub.domain.topico.Topico;
import com.alura.forohub.domain.topico.TopicoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
    Este Controller recibe requests de la aplicación Cliente.
 */

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    // Instanciar para poder guardar en
    @Autowired
    private TopicoRepository topicoRepository;

    @Transactional
    @PostMapping
    public void registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        topicoRepository.save(new Topico(datosRegistroTopico));     //Registrar en la DB
    }

    @GetMapping
    public String listarTopicos(){
        return "Todo Correcto con listarTopicos";
    }


}
